package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.service.AccidentService;

/**
 * IndexController.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 28.03.2023.
 */
@Controller
@AllArgsConstructor
public class IndexController {
    private final AccidentService accidentService;
    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("accidents", this.accidentService.getAllAccidents());
        return "index";
    }
}
