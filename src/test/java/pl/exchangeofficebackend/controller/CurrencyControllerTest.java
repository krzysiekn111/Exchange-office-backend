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
import pl.exchangeofficebackend.domain.dto.CurrencyDto;
import pl.exchangeofficebackend.facade.CurrencyControllerFacade;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(CurrencyController.class)
@SpringJUnitWebConfig
public class CurrencyControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyControllerFacade currencyControllerFacade;

    @Test
    void testFindCurrencies() throws Exception {
        //given
        List<CurrencyDto> currencyDtos = List.of(new CurrencyDto(10L, "symbol", "name"));
        when(currencyControllerFacade.findCurrencies()).thenReturn(currencyDtos);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/office/currency")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].symbol", Matchers.is("symbol")));
    }

    @Test
    void testFindCurrency() throws Exception {
        //given
        Long id = 10L;
        CurrencyDto currencyDto = new CurrencyDto(10L, "symbol", "name");
        when(currencyControllerFacade.findCurrency(id)).thenReturn(currencyDto);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/office/currency/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbol", Matchers.is("symbol")));
    }

    @Test
    void testSaveCurrency() throws Exception {
        //given
        Currency savedCurrency = new Currency(10L, "symbol", "name");
        CurrencyDto currencyDto = new CurrencyDto(10L, "symbol", "name");

        when(currencyControllerFacade.saveCurrency(any(CurrencyDto.class))).thenReturn(savedCurrency);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(currencyDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/office/currency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbol", Matchers.is("symbol")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("name")));
    }
}
