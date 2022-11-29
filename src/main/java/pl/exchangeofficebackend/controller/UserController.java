package pl.exchangeofficebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.exchangeofficebackend.domain.User;
import pl.exchangeofficebackend.domain.dto.UserDto;
import pl.exchangeofficebackend.facade.UserControllerFacade;
import pl.exchangeofficebackend.mapper.UserMapper;
import pl.exchangeofficebackend.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/office/user")
public class UserController {

    @Autowired
    private UserControllerFacade userControllerFacade;

    @GetMapping
    private ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userControllerFacade.findAll());
    }

    @GetMapping(value = "{userId}")
    private UserDto findUser(@PathVariable Long userId) throws Exception {
        return userControllerFacade.findUser(userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<User> saveUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userControllerFacade.saveUser(userDto));
    }

    @DeleteMapping(value = "{userId}")
    private ResponseEntity<Void> deleteUser(@PathVariable long userId){
        userControllerFacade.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
