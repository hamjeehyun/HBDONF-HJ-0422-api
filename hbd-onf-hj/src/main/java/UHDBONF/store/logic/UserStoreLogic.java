package UHDBONF.store.logic;

import UHDBONF.domain.dto.UserDto;
import UHDBONF.domain.entity.User;
import UHDBONF.store.UserStore;
import UHDBONF.store.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserStoreLogic implements UserStore {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserStoreLogic.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public String create(UserDto userDto) {
        User user = new User(userDto);

        if (userRepository.existsById(userDto.getUid())) {
            throw new RuntimeException("exists uid : " + userDto.getUid());
        }

        userRepository.save(user);
        return user.getUid();
    }
    @Override
    public String update(UserDto userDto) {
        User user = new User(userDto);
        userRepository.save(user);
        return user.getUid();
    }

    @Override
    public UserDto readByTid(String tid) {
        User user = userRepository.findByTid(tid);

        if(user == null) {
            return null;
        }

        return user.toDto();
    }

    @Override
    public UserDto readByUid(String uid) {
        User user = userRepository.findByUid(uid);

        if(user == null) {
            return null;
        }

        return user.toDto();
    }

    @Override
    public List<User> readAllUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public boolean existsByUid(String uid) {
        return userRepository.existsByUid(uid);
    }
}
