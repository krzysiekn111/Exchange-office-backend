package pl.exchangeofficebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.exchangeofficebackend.domain.ExchangeRates;
import pl.exchangeofficebackend.domain.dto.ExchangeRatesDto;
import pl.exchangeofficebackend.facade.ExchangeRatesControllerFacade;
import pl.exchangeofficebackend.mapper.ExchangeRatesMapper;
import pl.exchangeofficebackend.service.ExchangeRatesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/office/rates")
public class ExchangeRatesController {

    @Autowired
    ExchangeRatesControllerFacade exchangeRatesControllerFacade;

    @GetMapping
    private ResponseEntity<List<ExchangeRatesDto>> showExchangeRates() {
        return ResponseEntity.ok(exchangeRatesControllerFacade.showExchangeRates());
    }

    @GetMapping(value = "{rateId}")
    private ExchangeRatesDto showExchangeRate(@PathVariable Long rateId) throws Exception {
        return exchangeRatesControllerFacade.showExchangeRate(rateId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<ExchangeRates> saveExchangeRate(@RequestBody ExchangeRatesDto exchangeRatesDto) throws Exception {
        return ResponseEntity.ok(exchangeRatesControllerFacade.saveExchangeRate(exchangeRatesDto));
    }


}
