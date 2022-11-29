package pl.exchangeofficebackend.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.exchangeofficebackend.domain.ExchangeRates;
import pl.exchangeofficebackend.domain.dto.ExchangeRatesDto;
import pl.exchangeofficebackend.mapper.CurrencyMapper;
import pl.exchangeofficebackend.mapper.ExchangeRatesMapper;
import pl.exchangeofficebackend.service.CurrencyService;
import pl.exchangeofficebackend.service.ExchangeRatesService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExchangeRatesControllerFacade {

    @Autowired
    ExchangeRatesService exchangeRatesService;
    @Autowired
    ExchangeRatesMapper exchangeRatesMapper;

    public List<ExchangeRatesDto> showExchangeRates() {
        return exchangeRatesMapper.mapToExchangeRatesDtoList(exchangeRatesService.findExchangeRates());
    }

    public ExchangeRatesDto showExchangeRate( Long rateId) throws Exception {
        return exchangeRatesMapper.mapToExchangeRatesDto(exchangeRatesService.findExchangeRateById(rateId));
    }

    public ExchangeRates saveExchangeRate(ExchangeRatesDto exchangeRatesDto) throws Exception {
        return exchangeRatesService.saveExchangeRate(exchangeRatesMapper.mapToExchangeRates(exchangeRatesDto));
    }
}
