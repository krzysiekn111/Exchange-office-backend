package pl.exchangeofficebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.exchangeofficebackend.domain.ExchangeRates;
import pl.exchangeofficebackend.domain.dto.ExchangeRatesDto;
import pl.exchangeofficebackend.mapper.ExchangeRatesMapper;
import pl.exchangeofficebackend.service.ExchangeRatesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/office/rates")
public class ExchangeRatesController {

    @Autowired
    private ExchangeRatesService exchangeRatesService;
    @Autowired
    private ExchangeRatesMapper exchangeRatesMapper;

    @GetMapping
    private ResponseEntity<List<ExchangeRatesDto>> showExchangeRates() {
        List<ExchangeRates> exchangeRates = exchangeRatesService.findExchangeRates();
        return ResponseEntity.ok(exchangeRatesMapper.mapToExchangeRatesDtoList(exchangeRates));
    }

    @GetMapping(value = "{rateId}")
    private ExchangeRates showExchangeRate(@PathVariable Long rateId) throws Exception {
        return exchangeRatesService.findExchangeRateById(rateId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Void> saveExchangeRate(@RequestBody ExchangeRatesDto exchangeRatesDto) throws Exception {
        exchangeRatesService.saveExchangeRate(exchangeRatesMapper.mapToExchangeRates(exchangeRatesDto));
        return ResponseEntity.ok().build();
    }
}
