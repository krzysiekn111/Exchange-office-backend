package pl.exchangeofficebackend.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.exchangeofficebackend.domain.dto.UserDto;
import pl.exchangeofficebackend.mapper.UserMapper;
import pl.exchangeofficebackend.service.UserService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserControllerFacade {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    public List<UserDto> findAll() {
        return userMapper.mapToUserDtoList(userService.findUsers());
    }

    public UserDto findUser(Long id) throws Exception {
        return userMapper.mapToUserDto(userService.findUserById(id));
    }
}
