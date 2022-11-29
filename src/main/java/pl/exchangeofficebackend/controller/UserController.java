package pl.exchangeofficebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.exchangeofficebackend.domain.User;
import pl.exchangeofficebackend.domain.dto.UserDto;
import pl.exchangeofficebackend.mapper.UserMapper;
import pl.exchangeofficebackend.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/office/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping
    private ResponseEntity<List<UserDto>> findAll() {
        List<User> users = userService.findUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(users));
    }

    @GetMapping(value = "{userId}")
    private User findUser(@PathVariable Long userId) throws Exception {
        return userService.findUserById(userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Void> saveUser(@RequestBody UserDto userDto) throws Exception {
        userService.saveUser(userMapper.mapToPlainUser(userDto));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{userId}")
    private ResponseEntity<Void> deleteUser(@PathVariable long userId) throws Exception {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
