package UHDBONF.service.logic;

import UHDBONF.domain.dto.LuckyDrawDto;
import UHDBONF.domain.dto.UserDto;
import UHDBONF.service.LuckyDrawService;
import UHDBONF.store.LuckyDrawStore;
import UHDBONF.store.UserStore;
import UHDBONF.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LuckyDrawServiceLogic implements LuckyDrawService {
    private final static Logger LOGGER = LoggerFactory.getLogger(LuckyDrawServiceLogic.class);

    @Autowired
    private LuckyDrawStore luckyDrawStore;
    @Autowired
    private UserStore userStore;

    @Override
    public String registerLuckyDrawByUserUid(String userUid) {
        // 사용자가 응모한 럭드 중에 이미 공개 된 것이 있으면 예외
        if (this.findAllOpenLuckyDrawByUserUid(userUid).size() > 0) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("이미 럭키드로우에 응모했습니다.");
            }
            throw ExceptionUtil.createOnfBizException("이미 럭키드로우에 응모했습니다.");
        }

        LuckyDrawDto luckyDrawDto = new LuckyDrawDto();

        // 사용자 셋팅
        UserDto userDto = userStore.readByUid(userUid);
        if (userDto==null) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("등록되지 않은 사용자입니다.");
            }
            throw ExceptionUtil.createOnfBizException("등록되지 않은 사용자입니다.");
        }
        luckyDrawDto.setUser(userDto);

        int luckyDrawSize = luckyDrawStore.readMaxSequence();
        if (luckyDrawSize!=-1) {
            luckyDrawDto.setSequence(luckyDrawSize+1);
        }

        LocalDateTime now = LocalDateTime.now();
        luckyDrawDto.setCreated(now);
        luckyDrawDto.setUpdated(now);

        // 공개
        luckyDrawDto.setExpose(true);
        return luckyDrawStore.create(luckyDrawDto);
    }

    @Override
    public LuckyDrawDto findLuckyDrawByUid(String uid) {
        return luckyDrawStore.readByUid(uid);
    }

    @Override
    public List<LuckyDrawDto> findAllOpenLuckyDrawByUserUid(String userUid) {
        return luckyDrawStore.realAllOpenLuckyDrawByUserUid(userUid);
    }

    @Override
    public String modifyLuckyDrawCloseByUid(String uid) {
        LuckyDrawDto luckyDrawDto = this.findLuckyDrawByUid(uid);
        if (luckyDrawDto == null) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("이미 럭키드로우에 응모했습니다.");
            }
            throw ExceptionUtil.createOnfBizException("이미 럭키드로우에 응모했습니다.");
        }

        LocalDateTime now = LocalDateTime.now();
        luckyDrawDto.setUpdated(now);

        luckyDrawDto.setExpose(false);
        return luckyDrawStore.create(luckyDrawDto);
    }
}
