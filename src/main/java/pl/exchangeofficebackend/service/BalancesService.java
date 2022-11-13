package pl.exchangeofficebackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.exchangeofficebackend.domain.Balances;
import pl.exchangeofficebackend.repository.BalancesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BalancesService {

    private BalancesRepository balancesRepository;

    @Autowired
    public BalancesService(BalancesRepository balancesRepository) {
        this.balancesRepository = balancesRepository;
    }

    public List<Balances> findBalances() {
        return balancesRepository.findAll();
    }

    public Balances findBalance(Long id) throws Exception {
        return balancesRepository.findById(id).orElseThrow(Exception::new);
    }

    public Balances saveBalance(Balances balances) {
        return balancesRepository.save(balances);
    }

    public void deleteBalance(Long id) {
        balancesRepository.deleteById(id);
    }

}
