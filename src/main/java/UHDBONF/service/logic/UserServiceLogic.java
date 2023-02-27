package UHDBONF.service.logic;

import UHDBONF.domain.dto.UserDto;
import UHDBONF.service.UserService;
import UHDBONF.store.UserStore;
import UHDBONF.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceLogic implements UserService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceLogic.class);

    @Autowired
    private UserStore userStore;

    @Override
    public String registerUser(UserDto userDto) {
        if (userDto.getUid() != null){
            LocalDateTime now = LocalDateTime.now();
            if(existsByUid(userDto.getUid())) {
                // 이미 사용자가 존재 - 로그인
                UserDto user = userStore.readByUid(userDto.getUid());
                user.setUpdated(now);

                return userStore.update(user);
            } else {
                // 사용자가 존재하지 않음 - 회원가입
                userDto.setCreated(now);
                userDto.setUpdated(now);
                return userStore.create(userDto);
            }
        }
        return null;
    }

    @Override
    public UserDto findUserByUid(String uid) {
        UserDto userDto = userStore.readByUid(uid);
        if (userDto==null) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("등록되지 않은 사용자입니다.");
            }
            throw ExceptionUtil.createOnfBizException("등록되지 않은 사용자입니다.");
        }
        return userDto;
    }

    @Override
    public Boolean existsByUid(String uid) {
        return userStore.existsByUid(uid);
    }
}
