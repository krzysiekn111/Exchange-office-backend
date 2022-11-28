package pl.exchangeofficebackend.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.exchangeofficebackend.domain.dto.UserDto;
import pl.exchangeofficebackend.mapper.UserMapper;
import pl.exchangeofficebackend.repository.UserRepository;
import pl.exchangeofficebackend.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserTestSuite {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Test
    void testAddUser() {
        //given
        User user1 = new User(101L, "username", "login", "password");
        User user2 = new User(102L, "username", "login", "password");
        User user3 = new User(103L, "username", "login", "password");
        //when
        User savedUser1 = userRepository.save(user1);
        User savedUser2 = userRepository.save(user2);
        User savedUser3 = userRepository.save(user3);
        //then
        assertTrue(userRepository.existsById(savedUser1.getId()));
        assertTrue(userRepository.existsById(savedUser2.getId()));
        assertTrue(userRepository.existsById(savedUser3.getId()));
        //cleanUp
        userRepository.deleteById(savedUser1.getId());
        userRepository.deleteById(savedUser2.getId());
        userRepository.deleteById(savedUser3.getId());
    }

    @Test
    void testDToMapper() {
        //given
        User user1 = new User(101L, "username", "login", "password");
        //when
        User savedUser1 = userRepository.save(user1);
        List<User> users = new ArrayList<>();
        users.add(savedUser1);
        List<UserDto> userDtos = userMapper.mapToUserDtoList(users);
        UserDto userDto = userMapper.mapToUserDto(user1);

        //then
        assertEquals(101L, userDto.getId());
        assertEquals("username", userDto.getUserName());
        assertEquals("login", userDto.getLogin());
        assertEquals("password", userDto.getPassword());
        assertEquals(users.get(0).getUserName(), userMapper.mapToUserList(userDtos).get(0).getUserName());
        //cleanUp
        userRepository.deleteById(savedUser1.getId());
    }
}
