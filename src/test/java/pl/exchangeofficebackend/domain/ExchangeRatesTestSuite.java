package pl.exchangeofficebackend.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.exchangeofficebackend.domain.dto.ExchangeRatesDto;
import pl.exchangeofficebackend.domain.dto.HistoryDto;
import pl.exchangeofficebackend.mapper.ExchangeRatesMapper;
import pl.exchangeofficebackend.repository.ExchangeRatesRepository;
import pl.exchangeofficebackend.service.CurrencyService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ExchangeRatesTestSuite {

    @Autowired
    ExchangeRatesRepository exchangeRatesRepository;
    @Autowired
    ExchangeRatesMapper exchangeRatesMapper;
    @Autowired
    CurrencyService currencyService;

    @Test
    void testAddExchangeRate() throws Exception {
        //given
        ExchangeRates rate1 = new ExchangeRates(101L,
                currencyService.findCurrencyById(4L), 3.0F);
        ExchangeRates rate2 = new ExchangeRates(102L,
                currencyService.findCurrencyById(4L), 3.0F);
        ExchangeRates rate3 = new ExchangeRates(103L,
                currencyService.findCurrencyById(4L), 3.0F);
        //when
        ExchangeRates savedRate1 = exchangeRatesRepository.save(rate1);
        ExchangeRates savedRate2 = exchangeRatesRepository.save(rate2);
        ExchangeRates savedRate3 = exchangeRatesRepository.save(rate3);
        //then
        assertTrue(exchangeRatesRepository.existsById(savedRate1.getId()));
        assertTrue(exchangeRatesRepository.existsById(savedRate2.getId()));
        assertTrue(exchangeRatesRepository.existsById(savedRate3.getId()));
        //cleanUp
        exchangeRatesRepository.deleteById(savedRate1.getId());
        exchangeRatesRepository.deleteById(savedRate2.getId());
        exchangeRatesRepository.deleteById(savedRate3.getId());
    }

    @Test
    void testExchangeRateDToMapper() throws Exception  {
        //given
        ExchangeRates rate1 = new ExchangeRates(101L,
                currencyService.findCurrencyById(4L), 3.0F);
        //when
        ExchangeRates savedRate1 = exchangeRatesRepository.save(rate1);
        List<ExchangeRates> rates = new ArrayList<>();
        rates.add(savedRate1);
        List<ExchangeRatesDto> rateDTos = exchangeRatesMapper.mapToExchangeRatesDtoList(rates);
        ExchangeRatesDto rateDTo = exchangeRatesMapper.mapToExchangeRatesDto(rate1);

        //then
        assertEquals(101L, rateDTo.getId());
        assertEquals(4L, rateDTo.getCurrencyId());
        assertEquals(3.0F, rateDTo.getExchangeRateToPLN());
        assertEquals("funt brytyjski", rateDTo.getCurrencyName());
        //cleanUp
        exchangeRatesRepository.deleteById(savedRate1.getId());
    }
}
