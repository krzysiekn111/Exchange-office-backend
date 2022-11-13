package pl.exchangeofficebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.exchangeofficebackend.domain.Balances;
import pl.exchangeofficebackend.domain.Currency;
import pl.exchangeofficebackend.service.BalancesService;
import pl.exchangeofficebackend.service.CurrencyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/office/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    private List<Currency> findCurrencies() {
        return currencyService.findCurrencies();
    }

    @GetMapping(value = "{currencyId}")
    private Currency findCurrency(@PathVariable Long currencyId) throws Exception {
        return currencyService.findCurrencyById(currencyId);
    }

}
