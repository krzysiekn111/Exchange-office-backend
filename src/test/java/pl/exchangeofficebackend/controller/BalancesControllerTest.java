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
import pl.exchangeofficebackend.domain.Balances;
import pl.exchangeofficebackend.domain.Currency;
import pl.exchangeofficebackend.domain.dto.BalancesDto;
import pl.exchangeofficebackend.domain.dto.CurrencyDto;
import pl.exchangeofficebackend.facade.BalancesControllerFacade;
import pl.exchangeofficebackend.service.CurrencyService;
import pl.exchangeofficebackend.service.UserService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(BalancesController.class)
@SpringJUnitWebConfig
public class BalancesControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BalancesControllerFacade balancesControllerFacade;
    @MockBean
    private UserService userService;
    @MockBean
    private CurrencyService currencyService;

    @Test
    void testFindBalances() throws Exception {
        //given
        List<BalancesDto> balancesDtos = List.of(new BalancesDto(1L, 5));
        when(balancesControllerFacade.findBalances()).thenReturn(balancesDtos);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/office/balances")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].quantity", Matchers.is(5)));
    }

    @Test
    void testFindBalance() throws Exception {
        //given
        Long id = 5L;
        BalancesDto balancesDto = new BalancesDto(5L, 5);
        when(balancesControllerFacade.findBalance(id)).thenReturn(balancesDto);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/office/balances/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", Matchers.is(5)));
    }

    @Test
    void testSaveBalance() throws Exception {
        //given
        Balances savedBalances = new Balances(1L, 5);
        BalancesDto balancesDto = new BalancesDto(1L, 5);

        when(balancesControllerFacade.saveBalance(any(BalancesDto.class))).thenReturn(savedBalances);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(balancesDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/office/balances")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", Matchers.is(5)));
    }
}
