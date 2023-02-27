package UHDBONF.controller;

import UHDBONF.domain.dto.UserDto;
import UHDBONF.service.UserProcess;
import UHDBONF.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hj/users")
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserProcess userProcess;

    @PostMapping
    public String registerUser(@RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }

     @GetMapping("uid/{uid}/open")
     public UserDto findOpenUserByUid(@PathVariable(name="uid") String uid){
        return userProcess.findOpenUserByUid(uid);
     }
}
