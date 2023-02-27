package UHDBONF.store;

import UHDBONF.domain.dto.MessageDto;

import java.util.List;

public interface MessageStore {
    String create(MessageDto messageDto);
    MessageDto readByMessageUid(String uid);
    MessageDto readByUserId(String userId);
    List<MessageDto> readAllByTid(String tid);
    List<MessageDto> readAllByUserUid(String userUid);
    List<MessageDto> readAllMessagesOrderByCreatedAtDesc();
    List<MessageDto> readAllOpenMessagesOrderByCreatedAtDesc();
    List<MessageDto> readAllMessagesBySize(int size);
    List<MessageDto> readAllOpenMessagesBySize(int size);
    List<MessageDto> readAllOpenMessageByTid(String tid);
    List<MessageDto> readAllOpenMessageByUserUid(String uid);
}
