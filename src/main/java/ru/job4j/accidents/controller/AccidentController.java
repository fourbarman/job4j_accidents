package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;

import java.util.Optional;

/**
 * AccidentController.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 03.04.2023.
 */
@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidents;
    private final AccidentTypeService types;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", this.types.getAllTypes());
        return "accident/createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        Optional<AccidentType> type = this.types.findTypeById(accident.getType().getId());
        if (type.isEmpty()) {
            return "redirect:/";
        }
        accident.setType(type.get());
        accidents.create(accident);
        return "redirect:/";
    }

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam("id") int id, Model model) {
        Optional<Accident> accident = this.accidents.findById(id);
        if (accident.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("types", this.types.getAllTypes());
        model.addAttribute("accident", accident.get());
        return "/accident/updateAccident";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident) {
        Optional<AccidentType> type = this.types.findTypeById(accident.getType().getId());
        if (type.isEmpty()) {
            return "redirect:/";
        }
        accident.setType(type.get());
        accidents.updateAccident(accident);
        return "redirect:/";
    }
}
