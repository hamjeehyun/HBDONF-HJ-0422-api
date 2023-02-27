package UHDBONF.service;

import UHDBONF.domain.dto.UserDto;

public interface UserService {
    String registerUser(UserDto userDto);
    UserDto findUserByUid(String uid);
    Boolean existsByUid(String uid);
}
