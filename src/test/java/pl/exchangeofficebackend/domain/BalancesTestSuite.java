package pl.exchangeofficebackend.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.exchangeofficebackend.domain.dto.BalancesDto;
import pl.exchangeofficebackend.domain.dto.ExchangeRatesDto;
import pl.exchangeofficebackend.mapper.BalancesMapper;
import pl.exchangeofficebackend.repository.BalancesRepository;
import pl.exchangeofficebackend.repository.UserRepository;
import pl.exchangeofficebackend.service.CurrencyService;
import pl.exchangeofficebackend.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BalancesTestSuite {

    @Autowired
    BalancesRepository balancesRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BalancesMapper balancesMapper;
    @Autowired
    UserService userService;
    @Autowired
    CurrencyService currencyService;

    @Test
    void testAddBalances() throws Exception {
        //given
        User user1 = new User(101L, "username", "login", "password");
        User savedUser1 = userRepository.save(user1);
        Balances balance1 = new Balances(101L, userService.findUserById(savedUser1.getId()),
                currencyService.findCurrencyById(4L), 10);
        Balances balance2 = new Balances(102L, userService.findUserById(savedUser1.getId()),
                currencyService.findCurrencyById(4L), 10);
        Balances balance3 = new Balances(103L, userService.findUserById(savedUser1.getId()),
                currencyService.findCurrencyById(4L), 10);
        //when
        Balances savedBalance1 = balancesRepository.save(balance1);
        Balances savedBalance2 = balancesRepository.save(balance2);
        Balances savedBalance3 = balancesRepository.save(balance3);
        //then
        assertTrue(balancesRepository.existsById(savedBalance1.getId()));
        assertTrue(balancesRepository.existsById(savedBalance2.getId()));
        assertTrue(balancesRepository.existsById(savedBalance3.getId()));
        //cleanUp
//        userRepository.deleteById(savedUser1.getId());
        balancesRepository.deleteById(savedBalance1.getId());
        balancesRepository.deleteById(savedBalance2.getId());
        balancesRepository.deleteById(savedBalance3.getId());
    }

    @Test
    void testBalancesDToMapper() throws Exception  {
        //given
        User user1 = new User(101L, "username", "login", "password");
        User savedUser1 = userRepository.save(user1);
        Balances balance1 = new Balances(101L,userService.findUserById(savedUser1.getId()),
                currencyService.findCurrencyById(4L), 10);
        //when
        Balances savedBalance1 = balancesRepository.save(balance1);
        List<Balances> balances = new ArrayList<>();
        balances.add(savedBalance1);
        List<BalancesDto> balancesDtos = balancesMapper.mapToListDto(balances);
        BalancesDto balanceDto = balancesMapper.mapToBalancesDto(balance1);

        //then
        assertEquals(4L, balanceDto.getCurrencyId());
        assertEquals(10, balanceDto.getQuantity());
//        assertEquals(101L, balanceDto.getUserId());
        //cleanUp
//        userRepository.deleteById(savedUser1.getId());
        balancesRepository.deleteById(savedBalance1.getId());
    }
}
