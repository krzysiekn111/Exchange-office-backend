package pl.exchangeofficebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.exchangeofficebackend.domain.History;
import pl.exchangeofficebackend.domain.User;
import pl.exchangeofficebackend.service.BalancesService;
import pl.exchangeofficebackend.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/office/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    private List<User> findAll() {
        return userService.findUsers();
    }

    @GetMapping(value = "{userId}")
    private User findHistory(@PathVariable Long userId) throws Exception {
        return userService.findUserById(userId);
    }
}
