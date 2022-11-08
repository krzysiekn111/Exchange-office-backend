package pl.exchangeofficebackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.exchangeofficebackend.repository.BalancesRepository;

@Service
@RequiredArgsConstructor
public class BalancesService {

    private BalancesRepository balancesRepository;

    @Autowired
    public BalancesService(BalancesRepository balancesRepository) {
        this.balancesRepository = balancesRepository;
    }
}
