package pl.exchangeofficebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.exchangeofficebackend.domain.Balances;
import pl.exchangeofficebackend.domain.Currency;
import pl.exchangeofficebackend.domain.dto.BalancesDto;
import pl.exchangeofficebackend.domain.dto.CurrencyDto;
import pl.exchangeofficebackend.mapper.CurrencyMapper;
import pl.exchangeofficebackend.service.BalancesService;
import pl.exchangeofficebackend.service.CurrencyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/office/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private CurrencyMapper currencyMapper;

    @GetMapping
    private List<Currency> findCurrencies() {
        return currencyService.findCurrencies();
    }

    @GetMapping(value = "{currencyId}")
    private Currency findCurrency(@PathVariable Long currencyId) throws Exception {
        return currencyService.findCurrencyById(currencyId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Void> saveCurrency(@RequestBody CurrencyDto currencyDto) throws Exception {
        currencyService.saveCurrency(currencyMapper.mapToPlaneCurrency(currencyDto));
        return ResponseEntity.ok().build();
    }

}
