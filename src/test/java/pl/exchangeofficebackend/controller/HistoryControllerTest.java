package pl.exchangeofficebackend.controller;

import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.exchangeofficebackend.domain.Currency;
import pl.exchangeofficebackend.domain.History;
import pl.exchangeofficebackend.domain.dto.CurrencyDto;
import pl.exchangeofficebackend.domain.dto.HistoryDto;
import pl.exchangeofficebackend.facade.CurrencyControllerFacade;
import pl.exchangeofficebackend.facade.HistoryControllerFacade;
import pl.exchangeofficebackend.mapper.CurrencyMapper;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(HistoryController.class)
@SpringJUnitWebConfig
public class HistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CurrencyControllerFacade currencyControllerFacade;
    @MockBean
    private HistoryControllerFacade historyControllerFacade;
    @MockBean
    private CurrencyMapper currencyMapper;

    @Test
    void testFindCurrencies() throws Exception {
        //given
        LocalDateTime time = LocalDateTime.now();
        List<HistoryDto> historyDto = List.of(new HistoryDto(10L, 4L, 3L,
                10, 20, time, 2.0F));
        when(historyControllerFacade.findAll()).thenReturn(historyDto);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/office/history")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].exchangeRate", Matchers.is(2.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].soldQuantity", Matchers.is(20)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].boughtQuantity", Matchers.is(10)));
    }

    @Test
    void testFindCurrency() throws Exception {
        //given
        LocalDateTime time = LocalDateTime.now();
        Long id = 10L;
        HistoryDto historyDto = new HistoryDto(10L, 4L, 3L,
                10, 20, time, 2.0F);
        when(historyControllerFacade.findHistory(id)).thenReturn(historyDto);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/office/history/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.exchangeRate", Matchers.is(2.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.soldQuantity", Matchers.is(20)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.boughtQuantity", Matchers.is(10)));
    }

//    @Test
//    void testSaveCurrency() throws Exception {
//        //given
//        LocalDateTime time = LocalDateTime.now();
//        History savedHistory = new History(10L, currencyMapper.mapToCurrency(currencyControllerFacade.findCurrency(4L)),
//                currencyMapper.mapToCurrency(currencyControllerFacade.findCurrency(3L)),
//                10, 20, time, 2.0F);
//        HistoryDto historyDto = new HistoryDto(10L, 4L, 3L,
//                10, 20, time, 2.0F);
//
//        when(historyControllerFacade.saveHistory(any(HistoryDto.class))).thenReturn(savedHistory);
//
//        Gson gson = new Gson();
//        String jsonContent = gson.toJson(historyDto);
//        //When & Then
//        mockMvc
//                .perform(MockMvcRequestBuilders
//                        .post("/v1/office/history")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .characterEncoding("UTF-8")
//                        .content(jsonContent))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.exchangeRate", Matchers.is(2.0)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.soldQuantity", Matchers.is(20)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.boughtQuantity", Matchers.is(10)));
//    }

}
