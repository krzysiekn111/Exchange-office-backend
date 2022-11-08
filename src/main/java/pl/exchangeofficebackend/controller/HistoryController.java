package pl.exchangeofficebackend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.exchangeofficebackend.domain.History;
import pl.exchangeofficebackend.service.HistoryService;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping
    private List<History> findAll() {
        return historyService.findall();
    }

}
