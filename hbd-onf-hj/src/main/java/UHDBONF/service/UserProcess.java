package UHDBONF.service;

import UHDBONF.domain.dto.UserDto;

public interface UserProcess {
    UserDto findOpenUserByUid(String uid);
}
