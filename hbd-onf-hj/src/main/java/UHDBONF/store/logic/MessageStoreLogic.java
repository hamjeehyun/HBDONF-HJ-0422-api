package UHDBONF.store.logic;

import UHDBONF.domain.dto.MessageDto;
import UHDBONF.domain.entity.Message;
import UHDBONF.store.MessageStore;
import UHDBONF.store.criteria.MessageSearchKeys;
import UHDBONF.store.criteria.MessageSearchSpecs;
import UHDBONF.store.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MessageStoreLogic implements MessageStore {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public String create(MessageDto messageDto) {
        Message message = new Message(messageDto);
        if(messageDto == null) {
            return null;
        }
        messageRepository.save(message);
        return message.getUid();
    }

    @Override
    public MessageDto readByMessageUid(String uid) {
        Message message = messageRepository.findById(uid).orElse(null);
        if(message==null) {
            return null;
        }
        return message.toDto();
    }

    @Override
    public MessageDto readByUserId(String tid) {
        Message message = messageRepository.findByTid(tid);
        if(message == null) {
            return null;
        }
        return message.toDto();
    }

    @Override
    public List<MessageDto> readAllByTid(String tid) {
        List<Message> messages = messageRepository.findAllByTid(tid);
        return messages.stream().map(Message::toDto).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> readAllByUserUid(String userUid) {
        List<Message> messages = messageRepository.findAllByUserUid(userUid);
        return messages.stream().map(Message::toDto).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> readAllMessagesOrderByCreatedAtDesc() {
        List<Message> messages = messageRepository.findAll(Sort.by(Sort.Direction.DESC, "created"));
        return messages.stream().map(Message::toDto).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> readAllOpenMessagesOrderByCreatedAtDesc() {
        Map<MessageSearchKeys, Object> searchKeys = new HashMap<>();
        searchKeys.put(MessageSearchKeys.EXPOSE, true);

        List<Message> messages = messageRepository.findAll(MessageSearchSpecs.searchWith(searchKeys));

        return messages.stream().map(Message::toDto).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> readAllMessagesBySize(int size) {
        Page<Message> messages = messageRepository
                .findAll(PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "created")));
        return messages.stream().map(Message::toDto).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> readAllOpenMessagesBySize(int size) {
        Map<MessageSearchKeys, Object> searchKeys = new HashMap<>();
        Pageable pageable = PageRequest.of(0, size);
        searchKeys.put(MessageSearchKeys.EXPOSE, true);

        Page<Message> messages = messageRepository
                .findAll(MessageSearchSpecs.searchWith(searchKeys), pageable);
        return messages.stream().map(Message::toDto).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> readAllOpenMessageByTid(String tid) {
        Map<MessageSearchKeys, Object> searchKeys = new HashMap<>();

        searchKeys.put(MessageSearchKeys.TID, tid);
        searchKeys.put(MessageSearchKeys.EXPOSE, true);

        List<Message> messages = messageRepository.findAll(MessageSearchSpecs.searchWith(searchKeys));
        return messages.stream().map(Message::toDto).collect(Collectors.toList());
    }

    @Override
    public List<MessageDto> readAllOpenMessageByUserUid(String userUid) {
        Map<MessageSearchKeys, Object> searchKeys = new HashMap<>();

        searchKeys.put(MessageSearchKeys.USER_UID, userUid);
        searchKeys.put(MessageSearchKeys.EXPOSE, true);

        List<Message> messages = messageRepository.findAll(MessageSearchSpecs.searchWith(searchKeys));
        return messages.stream().map(Message::toDto).collect(Collectors.toList());
    }
}
