package pl.exchangeofficebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import pl.exchangeofficebackend.service.BalancesService;
import pl.exchangeofficebackend.service.HistoryService;

@Controller
@RestController
@RequiredArgsConstructor
public class BalancesController {

    @Autowired
    private BalancesService balancesService;
}
