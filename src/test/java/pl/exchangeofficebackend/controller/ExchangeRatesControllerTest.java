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
import pl.exchangeofficebackend.domain.ExchangeRates;
import pl.exchangeofficebackend.domain.dto.ExchangeRatesDto;
import pl.exchangeofficebackend.facade.CurrencyControllerFacade;
import pl.exchangeofficebackend.facade.ExchangeRatesControllerFacade;
import pl.exchangeofficebackend.mapper.CurrencyMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(ExchangeRatesController.class)
@SpringJUnitWebConfig
public class ExchangeRatesControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CurrencyMapper currencyMapper;
    @MockBean
    private ExchangeRatesControllerFacade exchangeRatesControllerFacade;
    @MockBean
    private CurrencyControllerFacade currencyControllerFacade;

    @Test
    void testFindCurrencies() throws Exception {
        //given
        List<ExchangeRatesDto> exchangeRatesDtos = List.of(new ExchangeRatesDto(10L, 4L, 1.5F, "currencyName"));
        when(exchangeRatesControllerFacade.showExchangeRates()).thenReturn(exchangeRatesDtos);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/office/rates")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].currencyName", Matchers.is("currencyName")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].exchangeRateToPLN", Matchers.is(1.5)));
    }

    @Test
    void testFindCurrency() throws Exception {
        //given

        Long id = 10L;
        ExchangeRatesDto exchangeRatesDto = new ExchangeRatesDto(10L, 4L, 1.5F, "currencyName");
        when(exchangeRatesControllerFacade.showExchangeRate(id)).thenReturn(exchangeRatesDto);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/office/rates/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currencyName", Matchers.is("currencyName")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.exchangeRateToPLN", Matchers.is(1.5)));
    }

    @Test
    void testSaveCurrency() throws Exception {
        //given
        ExchangeRatesDto exchangeRatesDto = new ExchangeRatesDto(10L, 4L, 1.5F, "currencyName");
        ExchangeRates savedExchangeRates = new ExchangeRates(10L,
                currencyMapper.mapToCurrency(currencyControllerFacade.findCurrency(4L)), 1.5F, "currencyName");

        when(exchangeRatesControllerFacade.saveExchangeRate(any(ExchangeRatesDto.class))).thenReturn(savedExchangeRates);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(exchangeRatesDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/office/rates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currencyName", Matchers.is("currencyName")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.exchangeRateToPLN", Matchers.is(1.5)));
    }
}
