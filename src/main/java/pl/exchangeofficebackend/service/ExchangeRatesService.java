package pl.exchangeofficebackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.exchangeofficebackend.repository.ExchangeRatesRepository;

@Service
@RequiredArgsConstructor
public class ExchangeRatesService {

    private ExchangeRatesRepository exchangeRatesRepository;

    @Autowired
    public ExchangeRatesService(ExchangeRatesRepository exchangeRatesRepository) {
        this.exchangeRatesRepository = exchangeRatesRepository;
    }
}
