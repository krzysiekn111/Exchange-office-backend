package pl.exchangeofficebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.exchangeofficebackend.domain.Currency;
import pl.exchangeofficebackend.domain.dto.CurrencyDto;
import pl.exchangeofficebackend.facade.CurrencyControllerFacade;
import pl.exchangeofficebackend.mapper.CurrencyMapper;
import pl.exchangeofficebackend.service.CurrencyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/office/currency")
public class CurrencyController {

    @Autowired
    private CurrencyControllerFacade currencyControllerFacade;

    @GetMapping
    private ResponseEntity<List<CurrencyDto>> findCurrencies() {
        return ResponseEntity.ok(currencyControllerFacade.findCurrencies());
    }

    @GetMapping(value = "{currencyId}")
    private CurrencyDto findCurrency(@PathVariable Long currencyId) throws Exception {
        return currencyControllerFacade.findCurrency(currencyId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Currency> saveCurrency(@RequestBody CurrencyDto currencyDto) {
        currencyControllerFacade.saveCurrency(currencyDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{CurrencyId}")
    private ResponseEntity<Void> deleteCurrency(@PathVariable long CurrencyId) {
        currencyControllerFacade.deleteCurrency(CurrencyId);
        return ResponseEntity.ok().build();
    }

}
