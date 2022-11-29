package pl.exchangeofficebackend.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.exchangeofficebackend.domain.dto.HistoryDto;
import pl.exchangeofficebackend.mapper.HistoryMapper;
import pl.exchangeofficebackend.repository.HistoryRepository;
import pl.exchangeofficebackend.service.CurrencyService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class HistoryTestSuite {

    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    CurrencyService currencyService;
    @Autowired
    HistoryMapper historyMapper;

    @Test
    void testAddHistory() throws Exception {
        //given
        History history1 = new History(101L,  currencyService.findCurrencyById(4L), currencyService.findCurrencyById(3L),
                5, 5, 1F);
        History history2 = new History(102L,  currencyService.findCurrencyById(4L), currencyService.findCurrencyById(3L),
                5, 5, 1F);
        History history3 = new History(103L, currencyService.findCurrencyById(4L), currencyService.findCurrencyById(3L),
                5, 5, 1F);
        //when
        History savedHistory1 = historyRepository.save(history1);
        History savedHistory2 = historyRepository.save(history2);
        History savedHistory3 = historyRepository.save(history3);
        //then
        assertTrue(historyRepository.existsById(savedHistory1.getId()));
        assertTrue(historyRepository.existsById(savedHistory2.getId()));
        assertTrue(historyRepository.existsById(savedHistory3.getId()));
        //cleanUp
        historyRepository.deleteById(savedHistory1.getId());
        historyRepository.deleteById(savedHistory2.getId());
        historyRepository.deleteById(savedHistory3.getId());
    }

    @Test
    void testHistoryDToMapper() throws Exception {
        //given
        History history1 = new History(101L,  currencyService.findCurrencyById(4L), currencyService.findCurrencyById(3L),
                5, 5, 1F);
        //when
        History savedHistory1 = historyRepository.save(history1);
        List<History> histories = new ArrayList<>();
        histories.add(savedHistory1);
        List<HistoryDto> historyDtos = historyMapper.mapToHistoryDtoList(histories);
        HistoryDto historyDto = historyMapper.mapToHistoryDto(history1);

        //then
        assertEquals(101L, historyDto.getId());
        assertEquals(3L, historyDto.getCurrencySoldId());
        assertEquals(4L, historyDto.getCurrencyBoughtID());
        assertEquals(5, historyDto.getBoughtQuantity());
        assertEquals(5, historyDto.getSoldQuantity());
        assertEquals(1F, historyDto.getExchangeRate());
        //cleanUp
        historyRepository.deleteById(savedHistory1.getId());
    }

}
