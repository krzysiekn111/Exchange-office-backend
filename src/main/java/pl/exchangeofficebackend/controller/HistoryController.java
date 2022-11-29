package pl.exchangeofficebackend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.exchangeofficebackend.domain.Currency;
import pl.exchangeofficebackend.domain.History;
import pl.exchangeofficebackend.domain.dto.ExchangeRatesDto;
import pl.exchangeofficebackend.domain.dto.HistoryDto;
import pl.exchangeofficebackend.facade.HistoryControllerFacade;
import pl.exchangeofficebackend.mapper.BalancesMapper;
import pl.exchangeofficebackend.mapper.HistoryMapper;
import pl.exchangeofficebackend.service.HistoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/office/history")
public class HistoryController {

    @Autowired
    private HistoryControllerFacade historyControllerFacade;

    @GetMapping
    private ResponseEntity<List<HistoryDto>> findAll() {
        return ResponseEntity.ok(historyControllerFacade.findAll());
    }

    @GetMapping(value = "{historyId}")
    private HistoryDto findHistory(@PathVariable Long historyId) throws Exception {
        return historyControllerFacade.findHistory(historyId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<History> saveHistory(@RequestBody HistoryDto historyDto) throws Exception {
        historyControllerFacade.saveHistory(historyDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{historyId}")
    private ResponseEntity<Void> deleteHistory(@PathVariable long historyId) throws Exception {
        historyControllerFacade.deleteHistory(historyId);
        return ResponseEntity.ok().build();
    }

}
