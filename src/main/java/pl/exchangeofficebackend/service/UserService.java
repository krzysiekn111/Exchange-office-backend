package pl.exchangeofficebackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.exchangeofficebackend.domain.Currency;
import pl.exchangeofficebackend.domain.History;
import pl.exchangeofficebackend.domain.User;
import pl.exchangeofficebackend.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(Exception::new);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void prepareData() {
        saveUser(new User(1L, "George Soros", "login", "password"));
    }
}
