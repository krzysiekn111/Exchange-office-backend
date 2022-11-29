package pl.exchangeofficebackend.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import pl.exchangeofficebackend.domain.Currency;
import pl.exchangeofficebackend.domain.dto.CurrencyDto;
import pl.exchangeofficebackend.mapper.CurrencyMapper;
import pl.exchangeofficebackend.service.CurrencyService;

import java.util.List;


@Component
@RequiredArgsConstructor
public class CurrencyControllerFacade {

    @Autowired
    CurrencyService currencyService;
    @Autowired
    CurrencyMapper currencyMapper;

    public List<CurrencyDto> findCurrencies() {
        return currencyMapper.mapToCurrenciesDtoList(currencyService.findCurrencies());
    }

    public CurrencyDto findCurrency(Long currencyId) throws Exception {
        return currencyMapper.mapToCurrencyDto(currencyService.findCurrencyById(currencyId));
    }

    public Currency saveCurrency(CurrencyDto currencyDto) {
        return currencyService.saveCurrency(currencyMapper.mapToPlaneCurrency(currencyDto));
    }

    public void deleteCurrency(long CurrencyId) {
        currencyService.deleteCurrency(CurrencyId);
    }
}
