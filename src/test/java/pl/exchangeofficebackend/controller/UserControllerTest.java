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
import pl.exchangeofficebackend.domain.User;
import pl.exchangeofficebackend.domain.dto.UserDto;
import pl.exchangeofficebackend.facade.UserControllerFacade;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
@SpringJUnitWebConfig
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserControllerFacade userControllerFacade;

    @Test
    void testFindUsers() throws Exception {
        //given
        List<UserDto> users = List.of(new UserDto(10L, "username", "login", "password"));
        when(userControllerFacade.findAll()).thenReturn(users);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/office/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName", Matchers.is("username")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].login", Matchers.is("login")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password", Matchers.is("password")));
    }

    @Test
    void testFindUser() throws Exception {
        //given
        Long id = 10L;
        UserDto userDto = new UserDto(10L, "username", "login", "password");
        when(userControllerFacade.findUser(id)).thenReturn(userDto);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/office/user/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName", Matchers.is("username")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.login", Matchers.is("login")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("password")));
    }

    @Test
    void testSaveUser() throws Exception {
        //given
        User savedUser = new User(10L, "username", "login", "password");
        UserDto userDto = new UserDto(10L, "username", "login", "password");

        when(userControllerFacade.saveUser(any(UserDto.class))).thenReturn(savedUser);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/office/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName", Matchers.is("username")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.login", Matchers.is("login")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("password")));
    }

//    @Test
//    void testDeleteUser() throws Exception {
//        //Given
//        Long id = 10L;
//        UserDto userDto = new UserDto(10L, "username", "login", "password");
//        when(userControllerFacade.findUser(id)).thenReturn(userDto);
//        //When & Then
//        mockMvc
//                .perform(MockMvcRequestBuilders
//                        .get("/v1/office/user/" + id)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.userName", Matchers.is("username")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.login", Matchers.is("login")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.is("password")));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .delete("/v1/tasks/" + id)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().is(404));
//    }
}