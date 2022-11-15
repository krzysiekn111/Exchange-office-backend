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
import java.util.stream.Collectors;

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

    public Balances mapToBalances(BalancesDto balancesDto) throws Exception {
        return new Balances(
                balancesDto.getId(),
                userService.findUserById(balancesDto.getUserId()),
                currencyService.findCurrencyById(balancesDto.getCurrencyId()),
                balancesDto.getQuantity());
    }

    public BalancesDto mapToBalancesDto(Balances balances) {
        return new BalancesDto(
                balances.getId(),
                balances.getUser().getId(),
                balances.getCurrency().getId(),
                balances.getQuantity());
    }

//    public Balances mapToPlainBalances(BalancesDto balancesDto) {
//        return new Balances(
//                balancesDto.getId(),
//                balancesDto.getQuantity());
//    }

    public List<BalancesDto> mapToListDto(List<Balances> balancesList) {
        return balancesList.stream()
                .map(this::mapToBalancesDto)
                .collect(Collectors.toList());

    }

    public List<Balances> mapToList(List<BalancesDto> balancesDtos) {
        return balancesDtos.stream()
                .map(balance -> {
                    try {
                        return mapToBalances(balance);
                } catch (Exception e) {
                        return null;
                }
        })
                .collect(Collectors.toList());

    }

}
