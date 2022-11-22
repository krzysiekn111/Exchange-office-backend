package pl.exchangeofficebackend.mapper;

import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.exchangeofficebackend.domain.ExchangeRates;
import pl.exchangeofficebackend.domain.dto.ExchangeRatesDto;
import pl.exchangeofficebackend.service.CurrencyService;
import pl.exchangeofficebackend.service.ExchangeRatesService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeRatesMapper {

    @Autowired
    private ExchangeRatesService exchangeRatesService;
    @Autowired
    private CurrencyService currencyService;

    public ExchangeRates mapToExchangeRates(ExchangeRatesDto exchangeRatesDto) throws Exception {
        return new ExchangeRates(
                exchangeRatesDto.getId(),
                currencyService.findCurrencyById(exchangeRatesDto.getCurrencyId()),
                exchangeRatesDto.getExchangeRateToPLN(),
                "");
    }

    public ExchangeRatesDto mapToExchangeRatesDto(ExchangeRates exchangeRates) {
        return new ExchangeRatesDto(
                exchangeRates.getId(),
                exchangeRates.getCurrency().getId(),
                exchangeRates.getExchangeRateToPLN(),
                exchangeRates.getCurrencyName()
        );
    }

    public List<ExchangeRates> mapToExchangeRatesList(List<ExchangeRatesDto> exchangeRatesDtos) {
        return exchangeRatesDtos.stream()
                .map(o -> {
                    try {
                        return mapToExchangeRates(o);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }

    public List<ExchangeRatesDto> mapToExchangeRatesDtoList(List<ExchangeRates> exchangeRates) {
        return exchangeRates.stream()
                .map(this::mapToExchangeRatesDto)
                .collect(Collectors.toList());
    }
}
