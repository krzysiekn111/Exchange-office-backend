package pl.exchangeofficebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.exchangeofficebackend.domain.Currency;
import pl.exchangeofficebackend.domain.ExchangeRates;
import pl.exchangeofficebackend.service.ExchangeRatesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/office/rates")
public class ExchangeRatesController {

    @Autowired
    private ExchangeRatesService exchangeRatesService;

    @GetMapping
    private List<ExchangeRates> showExchangeRates() {
        return exchangeRatesService.findExchangeRates();
    }

    @GetMapping(value = "{currencyId}")
    private ExchangeRates showExchangeRate(@PathVariable Long rateId) throws Exception {
        return exchangeRatesService.findExchangeRateById(rateId);
    }
}
