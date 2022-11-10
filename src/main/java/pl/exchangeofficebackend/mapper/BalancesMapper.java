package pl.exchangeofficebackend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.exchangeofficebackend.service.BalancesService;
import pl.exchangeofficebackend.service.CurrencyService;
import pl.exchangeofficebackend.service.UserService;

@Service
public class BalancesMapper {

    private BalancesService balancesService;
    private CurrencyService currencyService;
    private UserService userService;

    @Autowired
    public BalancesMapper(BalancesService balancesService, CurrencyService currencyService, UserService userService) {
        this.balancesService = balancesService;
        this.currencyService = currencyService;
        this.userService = userService;
    }

}
