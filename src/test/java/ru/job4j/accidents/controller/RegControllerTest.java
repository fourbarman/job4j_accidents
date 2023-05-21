package ru.job4j.accidents.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.Main;
import ru.job4j.accidents.model.Authority;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.service.AuthorityService;
import ru.job4j.accidents.service.UserService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * IndexControllerTest.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 18.05.2023.
 */
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@RequiredArgsConstructor
class RegControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private AuthorityService authorityService;

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test registrationPage().
     * Get /registration.
     * When User register success than link to /registration.
     *
     * @throws Exception Exception.
     */
    @Test
    public void whenRegisterAndSuccess() throws Exception {
        mockMvc.perform(get("/registration"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("registration"));
    }

    /**
     * Test registration().
     * Get /registration.
     * When User already exists than link to /registration with param constError=true.
     *
     * @throws Exception Exception
     */
    @Test
    public void whenRegisterAndUserAlreadyExistsThanShowErrorMessage() throws Exception {
        mockMvc.perform(get("/registration").param("constrError", "true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user", "errMessage"))
                .andExpect(model().attribute("errMessage", "Такой логин уже существует!"))
                .andExpect(view().name("registration"));
    }

    /**
     * Test registrationPage().
     * Post registration.
     * When user was found than return redirect:/registration?constrError=true.
     *
     * @throws Exception Exception.
     */
    @Test
    void whenRegistrationAndUserWasFoundThanRedirectToRegistration() throws Exception {
        when(userService.save(any(User.class))).thenReturn(Optional.empty());
        mockMvc.perform(post("/registration")
                        .param("username", "test")
                        .param("password", "test")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/registration?constrError=true"));
    }

    /**
     * Test registrationPage().
     * Post registration.
     * When user was not found than save and return redirect:/login.
     *
     * @throws Exception Exception.
     */
    @Test
    void whenRegistrationAndUserWasNotFoundThanRedirectToLogin() throws Exception {
        when(userService.save(any(User.class))).thenReturn(Optional.of(new User()));
        when(authorityService.findByAuthority(anyString())).thenReturn(new Authority(1, "ROLE_USER"));
        mockMvc.perform(post("/registration")
                        .param("username", "test")
                        .param("password", "test")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(userService).save(argument.capture());
        assertEquals(argument.getValue().getUsername(), "test");
    }
}