package pl.exchangeofficebackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.exchangeofficebackend.domain.Balances;
import pl.exchangeofficebackend.repository.BalancesRepository;
import pl.exchangeofficebackend.repository.HistoryRepository;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BalancesService {

    private BalancesRepository balancesRepository;
    private HistoryRepository historyRepository;

    @Autowired
    public BalancesService(BalancesRepository balancesRepository, HistoryRepository historyRepository) {
        this.balancesRepository = balancesRepository;
        this.historyRepository = historyRepository;
    }

    public List<Balances> findBalances() {
        return balancesRepository.findAll();
    }

    public Balances findBalance(Long id) throws Exception {
        return balancesRepository.findById(id).orElseThrow(Exception::new);
    }

    public Balances saveBalance(Balances balances) {
//        historyRepository.callFillUpHistory();
        return balancesRepository.save(balances);
    }

    public void deleteBalance(Long id) {
        balancesRepository.deleteById(id);
    }

}
