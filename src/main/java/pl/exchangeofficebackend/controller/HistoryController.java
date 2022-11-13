package pl.exchangeofficebackend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.exchangeofficebackend.domain.Currency;
import pl.exchangeofficebackend.domain.History;
import pl.exchangeofficebackend.service.HistoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/office/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping
    private List<History> findAll() {
        return historyService.findHistories();
    }

    @GetMapping(value = "{historyId}")
    private History findHistory(@PathVariable Long historyId) throws Exception {
        return historyService.findHistoryById(historyId);
    }

}
