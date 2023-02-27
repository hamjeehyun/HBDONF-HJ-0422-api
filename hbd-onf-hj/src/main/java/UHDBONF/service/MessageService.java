package UHDBONF.service;

import UHDBONF.domain.dto.MessageDto;

import java.util.List;

public interface MessageService {
    int registerMessageByUserUid(String userUid, MessageDto messageDto);
    List<MessageDto> findAllOpenMessagesOrderByCreatedAtDesc();
    List<MessageDto> findAllOpenMessagesByPage(int size);
    List<MessageDto> findAllOpenMessageByUserUid(String userUid);
    String modifyMessageCloseByUid(String uid);
}
