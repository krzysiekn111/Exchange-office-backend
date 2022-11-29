package pl.exchangeofficebackend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.exchangeofficebackend.domain.Currency;
import pl.exchangeofficebackend.domain.dto.CurrencyDto;
import pl.exchangeofficebackend.service.BalancesService;
import pl.exchangeofficebackend.service.CurrencyService;
import pl.exchangeofficebackend.service.ExchangeRatesService;
import pl.exchangeofficebackend.service.HistoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyMapper {

    @Autowired
    private ExchangeRatesService exchangeRatesService;
    @Autowired
    private BalancesService balancesService;
    @Autowired
    private CurrencyService currencyService;

    public Currency mapToCurrency(CurrencyDto currencyDto) throws Exception {
        return new Currency(
                currencyDto.getId(),
                currencyDto.getSymbol(),
                currencyDto.getName(),
                balancesService.findBalance(currencyDto.getId()).getCurrency().getBalances(),
                currencyService.findCurrencyById(currencyDto.getId()).getCurrencyBought(),
                currencyService.findCurrencyById(currencyDto.getId()).getCurrencySold(),
                exchangeRatesService.findExchangeRateById(currencyDto.getId()).getCurrency().getCurrency());
    }

    public Currency mapToPlaneCurrency(CurrencyDto currencyDto) {
        return new Currency(
                currencyDto.getId(),
                currencyDto.getSymbol(),
                currencyDto.getName());
    }

    public CurrencyDto mapToCurrencyDto(Currency currency) {
        return new CurrencyDto(
                currency.getId(),
                currency.getSymbol(),
                currency.getName());
    }

    public List<Currency> mapToCurrenciesList(List<CurrencyDto> currencyDtos) {
        return currencyDtos.stream()
                .map(a -> {
                    try {
                        return mapToPlaneCurrency(a);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }

    public List<CurrencyDto> mapToCurrenciesDtoList(List<Currency> currencies) {
        return currencies.stream()
                .map(this::mapToCurrencyDto)
                .collect(Collectors.toList());
    }
}
