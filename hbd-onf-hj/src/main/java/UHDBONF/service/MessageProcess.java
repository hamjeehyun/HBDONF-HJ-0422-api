package UHDBONF.service;

import UHDBONF.domain.dto.MessageDto;

import java.util.List;

public interface MessageProcess {
    List<MessageDto> findAllOpenMessageByUserUid(String userUid);
}
