package pl.exchangeofficebackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.exchangeofficebackend.domain.Currency;
import pl.exchangeofficebackend.repository.CurrencyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> findCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency findCurrencyById(Long id) throws Exception {
        return currencyRepository.findById(id).orElseThrow(Exception::new);
    }

    public Currency saveCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    public void deleteCurrency(Long id) {
        currencyRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void prepareData() {
        saveCurrency(new Currency(1L,"chf","frank szwajcarski"));
        saveCurrency(new Currency(2L,  "usd", "dolar amerykański"));
        saveCurrency(new Currency(3L, "eur","euro" ));
        saveCurrency(new Currency(4L, "gbp", "funt brytyjski"));
    }
}
