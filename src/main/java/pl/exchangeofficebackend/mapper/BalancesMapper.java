package pl.exchangeofficebackend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.exchangeofficebackend.domain.Balances;
import pl.exchangeofficebackend.domain.dto.BalancesDto;
import pl.exchangeofficebackend.service.CurrencyService;
import pl.exchangeofficebackend.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BalancesMapper {

    private final CurrencyService currencyService;
    private final UserService userService;

    @Autowired
    public BalancesMapper(CurrencyService currencyService, UserService userService) {
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
                balances.getQuantity(),
                balances.getCurrency().getId());
    }

    public List<BalancesDto> mapToListDto(List<Balances> balancesList) {
        return balancesList.stream()
                .map(this::mapToBalancesDto)
                .collect(Collectors.toList());

    }

}
