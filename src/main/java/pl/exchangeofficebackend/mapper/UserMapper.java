package pl.exchangeofficebackend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.exchangeofficebackend.domain.User;
import pl.exchangeofficebackend.domain.dto.UserDto;
import pl.exchangeofficebackend.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    @Autowired
    UserService userService;

    public User mapToUser(UserDto userDto) throws Exception {
        return new User(
                userDto.getId(),
                userDto.getUserName(),
                userDto.getLogin(),
                userDto.getPassword(),
                userService.findUserById(userDto.getId()).getBalances()
        );
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.getLogin(),
                user.getPassword());
    }

    public List<User> mapToUserList(List<UserDto> userDto) {
        return userDto.stream()
                .map(a -> {
                    try {
                        return mapToUser(a);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }

    public List<UserDto> mapToUserDtoList(List<User> userDto) {
        return userDto.stream()
                .map(a -> {
                    try {
                        return mapToUserDto(a);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }
}
