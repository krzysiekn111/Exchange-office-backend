package pl.exchangeofficebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.exchangeofficebackend.domain.Balances;
import pl.exchangeofficebackend.domain.Currency;
import pl.exchangeofficebackend.domain.dto.BalancesDto;
import pl.exchangeofficebackend.facade.BalancesControllerFacade;
import pl.exchangeofficebackend.mapper.BalancesMapper;
import pl.exchangeofficebackend.service.BalancesService;
import pl.exchangeofficebackend.service.CurrencyService;
import pl.exchangeofficebackend.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/office/balances")
public class BalancesController {

    @Autowired
    BalancesControllerFacade balancesControllerFacade;

    @GetMapping
    private ResponseEntity<List<BalancesDto>> findBalances() {
        return ResponseEntity.ok(balancesControllerFacade.findBalances());
    }

    @GetMapping(value = "{balanceId}")
    private BalancesDto findBalance(@PathVariable Long balanceId) throws Exception {
        return balancesControllerFacade.findBalance(balanceId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Balances> saveBalance(@RequestBody BalancesDto balanceDto) throws Exception {
    return ResponseEntity.ok(balancesControllerFacade.saveBalance(balanceDto));
    }

    @DeleteMapping(value = "{BalanceId}")
    private ResponseEntity<Void> deleteBalance(@PathVariable Long BalanceId) throws Exception {
        balancesControllerFacade.deleteBalance(BalanceId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "balance/{balanceId}/user/{userId}")
    private ResponseEntity<BalancesDto> assignUserToBalance(@PathVariable Long balanceId, @PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(balancesControllerFacade.assignUserToBalance(balanceId, userId));
    }

    @PutMapping(value = "balance/{balanceId}/currency/{currencyId}")
    private ResponseEntity<BalancesDto> assignCurrencyToBalance(@PathVariable Long balanceId, @PathVariable Long currencyId) throws Exception {
        return ResponseEntity.ok(balancesControllerFacade.assignCurrencyToBalance(balanceId, currencyId));
    }
}
