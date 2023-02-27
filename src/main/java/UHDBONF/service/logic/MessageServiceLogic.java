package UHDBONF.service.logic;

import UHDBONF.domain.dto.MessageDto;
import UHDBONF.domain.dto.SealDto;
import UHDBONF.domain.dto.UserDto;
import UHDBONF.service.MessageService;
import UHDBONF.store.MessageStore;
import UHDBONF.store.SealStore;
import UHDBONF.store.UserStore;
import UHDBONF.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceLogic implements MessageService {
    private final static Logger LOGGER = LoggerFactory.getLogger(MessageServiceLogic.class);
    @Autowired
    private MessageStore messageStore;
    @Autowired
    private UserStore userStore;
    @Autowired
    private SealStore sealStore;

    @Override
    public int registerMessageByUserUid(String userUid, MessageDto messageDto) {

        // 글자 수 제한- 140자
        if (messageDto.getContent().length() > 140) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("140자 이상의 메세지는 작성 할 수 없습니다.");
            }
            throw ExceptionUtil.createOnfBizException("140자 이상의 메세지는 작성 할 수 없습니다.");
        }

        // 사용자가 작성한 것 중에 공개 상태 메세지가 있으면 작성 불가
        if (this.findAllOpenMessageByUserUid(userUid).size() > 0) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("이미 작성한 메세지가 있습니다.");
            }
            throw ExceptionUtil.createOnfBizException("이미 작성한 메세지가 있습니다.");
        }

        // 사용자 셋팅
        UserDto userDto = userStore.readByUid(userUid);
        if (userDto==null) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("등록되지 않은 사용자입니다.");
            }
            throw ExceptionUtil.createOnfBizException("등록되지 않은 사용자입니다.");
        }
        messageDto.setUser(userDto);

        // 현재시간 셋팅
        LocalDateTime now = LocalDateTime.now();
        messageDto.setCreated(now);
        messageDto.setUpdated(now);

        // 공개
        messageDto.setExpose(true);

        // 랜덤 수 발행 seal의 개수 만큼
        int sealSize = sealStore.readAll().size();
        int keyNumber = (int)((Math.random()*10000)%sealSize);
        messageDto.setKeyNumber(keyNumber);
        // 메세지 등록
        messageStore.create(messageDto);


        return keyNumber;
    }

    @Override
    public List<MessageDto> findAllOpenMessagesOrderByCreatedAtDesc() {
        List<MessageDto> messageDtoList = messageStore.readAllOpenMessagesOrderByCreatedAtDesc();

        this.setMessageSeal(messageDtoList);

        return messageDtoList;
    }

    @Override
    public List<MessageDto> findAllOpenMessagesByPage(int size) {
        List<MessageDto> messageDtoList = messageStore.readAllOpenMessagesBySize(size);

        this.setMessageSeal(messageDtoList);

        return messageDtoList;
    }

    @Override
    public List<MessageDto> findAllOpenMessageByUserUid(String userUid) {
        List<MessageDto> messageDtoList = messageStore.readAllOpenMessageByUserUid(userUid);

        this.setMessageSeal(messageDtoList);

        return messageDtoList;
    }

    @Override
    public String modifyMessageCloseByUid(String uid) {
        MessageDto messageDto = messageStore.readByMessageUid(uid);

        LocalDateTime now = LocalDateTime.now();
        messageDto.setUpdated(now);
        messageDto.setExpose(false);
        return messageStore.create(messageDto);
    }

    private void setMessageSeal(List<MessageDto> messageDtoList) {
        messageDtoList.stream().forEach(messageDto -> {
            SealDto sealDto = sealStore.readById(String.valueOf(messageDto.getKeyNumber()));
            messageDto.setSeal(sealDto);
        });
    }
}
