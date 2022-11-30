package pl.exchangeofficebackend.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import pl.exchangeofficebackend.domain.Balances;
import pl.exchangeofficebackend.domain.dto.BalancesDto;
import pl.exchangeofficebackend.mapper.BalancesMapper;
import pl.exchangeofficebackend.service.BalancesService;
import pl.exchangeofficebackend.service.CurrencyService;
import pl.exchangeofficebackend.service.UserService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BalancesControllerFacade {

    @Autowired
    private BalancesService balancesService;
    @Autowired
    private UserService userService;
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private BalancesMapper balancesMapper;

    public List<BalancesDto> findBalances() {
        return balancesMapper.mapToListDto(balancesService.findBalances());
    }

    public BalancesDto findBalance(Long balanceId) throws Exception {
        return balancesMapper.mapToBalancesDto(balancesService.findBalance(balanceId));
    }

    public Balances saveBalance(BalancesDto balanceDto) throws Exception {
        return balancesService.saveBalance(balancesMapper.mapToBalances(balanceDto));
    }

    public void deleteBalance(Long BalanceId) throws Exception {
        balancesService.deleteBalance(BalanceId);
    }

    public Balances assignUserToBalance(Long balanceId, Long userId) throws Exception {
        Balances balances = balancesService.findBalance(balanceId);
        balances.setUser(userService.findUserById(userId));
        return balancesService.saveBalance(balances);
    }

    public Balances assignCurrencyToBalance(Long balanceId,Long currencyId) throws Exception {
        Balances balances = balancesService.findBalance(balanceId);
        balances.setCurrency(currencyService.findCurrencyById(currencyId));
        balances.setCurrencyName(currencyService.findCurrencyById(currencyId).getName());
        return balancesService.saveBalance(balances);
    }
}
