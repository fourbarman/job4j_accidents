package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }
}
