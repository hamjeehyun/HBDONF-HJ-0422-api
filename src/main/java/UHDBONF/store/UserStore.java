package UHDBONF.store;

import UHDBONF.domain.entity.User;
import UHDBONF.domain.dto.UserDto;

import java.util.List;

public interface UserStore {
    String create(UserDto userDto);
    String update(UserDto userDto);
    UserDto readByTid(String tid);
    UserDto readByUid(String uid);
    List<User> readAllUser();
    boolean existsByUid(String uid);
}
