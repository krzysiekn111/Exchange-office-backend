package pl.exchangeofficebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.exchangeofficebackend.domain.Balances;
import pl.exchangeofficebackend.service.BalancesService;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
public class BalancesController {

    @Autowired
    private BalancesService balancesService;

    @PostMapping
    private Balances saveBalance(Balances balances) {
        return balancesService.saveBalance(balances);
    }

//    @GetMapping
//    private List<Balances> findBalances() {
//        return balancesService.findBalances();
//    }
//
//    @GetMapping
//    private Balances findBalance(Long id) throws Exception {
//        return balancesService.findBalance(id);
//    }
}
