package pl.exchangeofficebackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.exchangeofficebackend.domain.Currency;
import pl.exchangeofficebackend.domain.ExchangeRates;
import pl.exchangeofficebackend.repository.ExchangeRatesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRatesService {

    private ExchangeRatesRepository exchangeRatesRepository;

    @Autowired
    public ExchangeRatesService(ExchangeRatesRepository exchangeRatesRepository) {
        this.exchangeRatesRepository = exchangeRatesRepository;
    }

    public List<ExchangeRates> findExchangeRates() {
        return exchangeRatesRepository.findAll();
    }

    public ExchangeRates findExchangeRateById(Long id) throws Exception {
        return exchangeRatesRepository.findById(id).orElseThrow(Exception::new);
    }

    public ExchangeRates saveCurrency(ExchangeRates exchangeRates) {
        return exchangeRatesRepository.save(exchangeRates);
    }
}
