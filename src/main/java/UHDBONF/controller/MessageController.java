package UHDBONF.controller;

import UHDBONF.domain.dto.MessageDto;
import UHDBONF.service.MessageProcess;
import UHDBONF.service.MessageService;
import UHDBONF.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hj/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageProcess messageProcess;

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageController.class);


    /**
     * 메세지 등록 by user-uid
     * @param userUid
     * @param messageDto
     * @return
     */
    @PostMapping("user-uid/{user-uid}")
    public int registerMessageByUserUid(@PathVariable(name = "user-uid") String userUid,
                                       @RequestBody MessageDto messageDto) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("효렌치파이 전시가 마감되었습니다.");
        }
        throw ExceptionUtil.createOnfBizException("효렌치파이 전시가 마감되었습니다.");
//        return messageService.registerMessageByUserUid(userUid, messageDto);
    }

    /**
     * 공개 메세지 목록 조회 - 등록 내림차순
     * @return
     */
    @GetMapping("open")
    public List<MessageDto> findAllOpenMessagesOrderByCreatedAtDesc() {
        return messageService.findAllOpenMessagesOrderByCreatedAtDesc();
    }

    /**
     * 공개된 메세지 목록 조회 - 원하는 갯수만큼 - 등록 내림차순
     * @param size
     * @return
     */
    @GetMapping("page/{size}/open")
    public List<MessageDto> findAllOpenMessagesByPage(@PathVariable(name = "size") int size) {
        return messageService.findAllOpenMessagesByPage(size);
    }

    /**
     * 메세지 목록 조회 - 사용자가 작성한 메세지 중 공개 상태인 메세지 조회 by 사용자의 uid
     * @return
     */
    @GetMapping("user-uid/{user-uid}/open")
    public List<MessageDto> findAllOpenMessageByUserUid(@PathVariable(name="user-uid") String userUid) {
        return messageProcess.findAllOpenMessageByUserUid(userUid);
    }

    /**
     * 메세지 비공개 설정
     * @param uid
     * @return
     */
    @PutMapping("uid/{uid}/close")
    public String modifyMessageCloseByUid(@PathVariable(name = "uid") String uid){
        return messageService.modifyMessageCloseByUid(uid);

    }
}
