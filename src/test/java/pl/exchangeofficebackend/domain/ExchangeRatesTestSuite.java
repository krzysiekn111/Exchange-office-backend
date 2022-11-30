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

    @Test
    void testExchangeRateMapper() throws Exception  {
        //given
        ExchangeRatesDto rateDto1 = new ExchangeRatesDto(101L,
                4L, 3.0F, "funt brytyjski");
        //when
        List<ExchangeRatesDto> ratesDtos = new ArrayList<>();
        ratesDtos.add(rateDto1);
        List<ExchangeRates> rates = exchangeRatesMapper.mapToExchangeRatesList(ratesDtos);
        ExchangeRates savedRate1 = exchangeRatesRepository.save(exchangeRatesMapper.mapToExchangeRates(rateDto1));

        //then
        assertEquals(4L, savedRate1.getCurrency().getId());
        assertEquals(3.0F, savedRate1.getExchangeRateToPLN());
        assertEquals("funt brytyjski", savedRate1.getCurrency().getName());

        assertEquals(savedRate1.getCurrency().getId(), rates.get(0).getCurrency().getId());
        assertEquals(3.0F, rates.get(0).getExchangeRateToPLN());
        assertEquals("funt brytyjski", rates.get(0).getCurrencyName());
        //cleanUp
        exchangeRatesRepository.deleteById(savedRate1.getId());
    }
}
