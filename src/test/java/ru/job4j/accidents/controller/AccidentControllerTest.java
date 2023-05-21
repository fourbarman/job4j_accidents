package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.Main;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;
import ru.job4j.accidents.service.RulesService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @MockBean
    private AccidentService accidentService;
    @MockBean
    private AccidentTypeService accidentTypeService;
    @MockBean
    private RulesService rulesService;

    /**
     * Test viewCreateAccident().
     * Get createAccident.
     * Should return view createAccident.
     *
     * @throws Exception Exception.
     */
    @Test
    @WithMockUser
    public void shouldReturnViewCreateAccident() throws Exception {
        mockMvc.perform(get("/createAccident"))
                .andExpect(status().isOk())
                .andExpect(view()
                        .name("accident/createAccident"));
    }

    /**
     * Test update.
     * Get formUpdateAccident.
     * When Accident found and updated then link to /accident/updateAccident.
     *
     * @throws Exception Exception.
     */
    @Test
    @WithMockUser
    public void shouldReturnViewUpdateIfAccidentFoundAndUpdated() throws Exception {
        when(accidentTypeService.getAllTypes())
                .thenReturn(List.of(new AccidentType(1, "type")));
        when(rulesService.getAllRules())
                .thenReturn(List.of(new Rule(1, "rule")));
        when(accidentService.findById(1))
                .thenReturn(Optional.of(
                        new Accident(1, "test", "test", "test",
                                new AccidentType(),
                                new HashSet<>())
                ));
        mockMvc.perform(get("/formUpdateAccident").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view()
                        .name("/accident/updateAccident"));
    }

    /**
     * Test viewCreateAccident().
     * Get formUpdateAccident.
     *
     * @throws Exception Exception.
     */
    @Test
    @WithMockUser
    public void shouldRedirectToIndexIfAccidentNotFound() throws Exception {
        when(accidentService.findById(anyInt())).thenReturn(Optional.empty());
        mockMvc.perform(get("/formUpdateAccident").param("id", "100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view()
                        .name("redirect:/"));
    }

    /**
     * Test save().
     * Post saveAccident.
     * When AccidentType not found then redirect to /.
     *
     * @throws Exception Exception.
     */
    @Test
    @WithMockUser
    void whenSaveAndAccidentTypeNotFoundThanRedirectToIndex() throws Exception {
        when(accidentTypeService.findTypeById(1)).thenReturn(Optional.empty());
        mockMvc.perform(post("/saveAccident")
                        .param("id", "0")
                        .param("name", "testName")
                        .param("text", "testText")
                        .param("address", "testAddress")
                        .param("type.id", "1")
                        .param("rIds", "1")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
        verifyNoInteractions(accidentService);
    }

    /**
     * Test save().
     * Post saveAccident.
     * When AccidentType was found than save and redirect to /.
     *
     * @throws Exception Exception.
     */
    @Test
    @WithMockUser
    void whenSaveAndAccidentTypeFoundThanSuccessAndRedirectToIndex() throws Exception {
        when(accidentTypeService.findTypeById(1))
                .thenReturn(Optional.of(new AccidentType()));
        when(rulesService.getRulesSet(new String[]{"1"}))
                .thenReturn(new HashSet<>(List.of(new Rule(1, "rule"))));
        mockMvc.perform(post("/saveAccident")
                        .param("id", "0")
                        .param("name", "testName")
                        .param("text", "testText")
                        .param("address", "testAddress")
                        .param("type.id", "1")
                        .param("rIds", "1")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).create(argument.capture());
        assertEquals(argument.getValue().getName(), "testName");
    }

    /**
     * Test update().
     * Post updateAccident.
     * When Accident not found then redirect to /.
     *
     * @throws Exception Exception.
     */
    @Test
    @WithMockUser
    void whenUpdateAndAccidentNotFoundThanRedirectToIndex() throws Exception {
        when(accidentTypeService.findTypeById(anyInt())).thenReturn(Optional.empty());
        mockMvc.perform(post("/updateAccident")
                        .param("id", "0")
                        .param("name", "testName")
                        .param("text", "testText")
                        .param("address", "testAddress")
                        .param("type.id", "1")
                        .param("rIds", "1")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
        verifyNoInteractions(accidentService);
    }

    /**
     * Test update().
     * Post updateAccident.
     * When Accident was found then update and redirect to /.
     *
     * @throws Exception Exception.
     */
    @Test
    @WithMockUser
    void whenUpdateAndAccidentFoundThanRedirectToIndex() throws Exception {
        when(accidentTypeService.findTypeById(1))
                .thenReturn(Optional.of(new AccidentType()));
        when(rulesService.getRulesSet(new String[]{"1"}))
                .thenReturn(new HashSet<>(List.of(new Rule(1, "rule"))));
        mockMvc.perform(post("/updateAccident")
                        .param("id", "0")
                        .param("name", "testName")
                        .param("text", "testText")
                        .param("address", "testAddress")
                        .param("type.id", "1")
                        .param("rIds", "1")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
        ArgumentCaptor<Accident> captor = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).updateAccident(captor.capture());
        assertEquals(captor.getValue().getName(), "testName");
    }
}