package pl.exchangeofficebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.exchangeofficebackend.domain.Balances;
import pl.exchangeofficebackend.domain.dto.BalancesDto;
import pl.exchangeofficebackend.mapper.BalancesMapper;
import pl.exchangeofficebackend.service.BalancesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/office/balances")
public class BalancesController {

    @Autowired
    private BalancesService balancesService;
    @Autowired
    private BalancesMapper balancesMapper;

    @GetMapping
    private List<Balances> findBalances() {
        return balancesService.findBalances();
    }

    @GetMapping(value = "{balanceId}")
    private Balances findBalance(@PathVariable Long balanceId) throws Exception {
        return balancesService.findBalance(balanceId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Void> saveBalance(@RequestBody BalancesDto balanceDto) throws Exception {
    balancesService.saveBalance(balancesMapper.mapToBalances(balanceDto));
    return ResponseEntity.ok().build();
    }
}
