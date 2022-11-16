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
import pl.exchangeofficebackend.mapper.BalancesMapper;
import pl.exchangeofficebackend.mapper.HistoryMapper;
import pl.exchangeofficebackend.service.HistoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/office/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;
    @Autowired
    private HistoryMapper historyMapper;

    @GetMapping
    private ResponseEntity<List<HistoryDto>> findAll() {
        List<History> histories = historyService.findHistories();
        return ResponseEntity.ok(historyMapper.mapToHistoryDtoList(histories));
    }

    @GetMapping(value = "{historyId}")
    private History findHistory(@PathVariable Long historyId) throws Exception {
        return historyService.findHistoryById(historyId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Void> saveHistory(@RequestBody HistoryDto historyDto) throws Exception {
        historyService.saveHistory(historyMapper.mapToHistory(historyDto));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{historyId}")
    public ResponseEntity<Void> deleteHistory(@PathVariable long historyId) throws Exception {
        historyService.deleteHistory(historyId);
        return ResponseEntity.ok().build();
    }

}
