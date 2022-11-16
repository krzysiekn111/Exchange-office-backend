package pl.exchangeofficebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.exchangeofficebackend.domain.Balances;
import pl.exchangeofficebackend.domain.Currency;
import pl.exchangeofficebackend.domain.dto.BalancesDto;
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
    private BalancesService balancesService;
    @Autowired
    private UserService userService;
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private BalancesMapper balancesMapper;

    @GetMapping
    private ResponseEntity<List<BalancesDto>> findBalances() {
        List<Balances> balancesDtos = balancesService.findBalances();
        return ResponseEntity.ok(balancesMapper.mapToListDto(balancesDtos));
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

    @DeleteMapping(value = "{BalanceId}")
    public ResponseEntity<Void> deleteBalance(@PathVariable long BalanceId) throws Exception {
        balancesService.deleteBalance(BalanceId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "balance/{balanceId}/user/{userId}")
    public ResponseEntity<BalancesDto> assignUserToBalance(@PathVariable Long balanceId, @PathVariable Long userId) throws Exception {
        Balances balances = balancesService.findBalance(balanceId);
        balances.setUser(userService.findUserById(userId));
        return ResponseEntity.ok(balancesMapper.mapToBalancesDto(balancesService.saveBalance(balances)));
    }

    @PutMapping(value = "balance/{balanceId}/currency/{currencyId}")
    public ResponseEntity<BalancesDto> assignCurrencyToBalance(@PathVariable Long balanceId, @PathVariable Long currencyId) throws Exception {
        Balances balances = balancesService.findBalance(balanceId);
        balances.setCurrency(currencyService.findCurrencyById(currencyId));
        return ResponseEntity.ok(balancesMapper.mapToBalancesDto(balancesService.saveBalance(balances)));
    }
}
