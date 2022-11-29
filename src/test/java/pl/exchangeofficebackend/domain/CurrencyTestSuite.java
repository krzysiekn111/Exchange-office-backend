package pl.exchangeofficebackend.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.exchangeofficebackend.domain.dto.CurrencyDto;
import pl.exchangeofficebackend.mapper.CurrencyMapper;
import pl.exchangeofficebackend.repository.CurrencyRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CurrencyTestSuite {

    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private CurrencyMapper currencyMapper;

    @Test
    void testAddCurrency() {
        //given
        Currency currency1 = new Currency(101L,  "symbol", "name");
        Currency currency2 = new Currency(102L,  "symbol", "name");
        Currency currency3 = new Currency(103L, "symbol", "name");
        //when
        Currency savedCurrency1 = currencyRepository.save(currency1);
        Currency savedCurrency2 = currencyRepository.save(currency2);
        Currency savedCurrency3 = currencyRepository.save(currency3);
        //then
        assertTrue(currencyRepository.existsById(savedCurrency1.getId()));
        assertTrue(currencyRepository.existsById(savedCurrency2.getId()));
        assertTrue(currencyRepository.existsById(savedCurrency3.getId()));
        //cleanUp
        currencyRepository.deleteById(savedCurrency1.getId());
        currencyRepository.deleteById(savedCurrency2.getId());
        currencyRepository.deleteById(savedCurrency3.getId());
    }

    @Test
    void testCurrencyDToMapper() {
        //given
        Currency currency1 = new Currency(101L, "symbol", "name");
        //when
        Currency savedCurrency1 = currencyRepository.save(currency1);
        List<Currency> currencies = new ArrayList<>();
        currencies.add(savedCurrency1);
        List<CurrencyDto> currencyDtos = currencyMapper.mapToCurrenciesDtoList(currencies);
        CurrencyDto currencyDto = currencyMapper.mapToCurrencyDto(currency1);

        //then
        assertEquals(101L, currencyDto.getId());
        assertEquals("name", currencyDto.getName());
        assertEquals("symbol", currencyDto.getSymbol());
        assertEquals(currencies.get(0).getName(), currencyMapper.mapToCurrenciesList(currencyDtos).get(0).getName());
        //cleanUp
        currencyRepository.deleteById(savedCurrency1.getId());
    }
}
