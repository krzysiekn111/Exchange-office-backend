package pl.exchangeofficebackend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.exchangeofficebackend.domain.Balances;
import pl.exchangeofficebackend.domain.dto.BalancesDto;
import pl.exchangeofficebackend.service.BalancesService;
import pl.exchangeofficebackend.service.CurrencyService;
import pl.exchangeofficebackend.service.UserService;

import java.util.ArrayList;
import java.util.List;

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

//    public Balances mapToBalances(BalancesDto balancesDto) {
//        return new Balances(
//                balancesDto.getId(),
//                balancesDto.getUserId(),
//                balancesDto.getCurrencyId(),
//                balancesDto.getQuantity());
//    }
//
//    public List<BalancesDto> mapToListDto(List<Balances> balancesList) {
//        List<BalancesDto> balancesDtos = new ArrayList<>();
//
//    }
//
//    public List<Balances> mapToList(List<BalancesDto> balancesDtos) {
//        List<Balances> balancesList = new ArrayList<>();
//
//    }

}
