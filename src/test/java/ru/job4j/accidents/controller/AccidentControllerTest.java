package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.Main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * AccidentControllerTest.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 18.05.2023.
 */
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class AccidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnViewCreateAccident() throws Exception {
        mockMvc.perform(get("/createAccident"))
                .andExpect(status().isOk())
                .andExpect(view()
                        .name("accident/createAccident"));
    }
    @Test
    @WithMockUser
    public void shouldReturnViewUpdateIfAccidentFoundAndUpdated() throws Exception {
        mockMvc.perform(get("/formUpdateAccident").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view()
                        .name("/accident/updateAccident"));
    }

    @Test
    @WithMockUser
    public void shouldRedirectToIndexIfAccidentNotFound() throws Exception {
        mockMvc.perform(get("/formUpdateAccident").param("id", "100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view()
                        .name("redirect:/"));
    }
}