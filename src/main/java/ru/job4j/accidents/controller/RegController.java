package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.service.AuthorityService;
import ru.job4j.accidents.service.UserService;

import java.util.Optional;

/**
 * RegController.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 15.05.2023.
 */
@Controller
@AllArgsConstructor
public class RegController {
    private final PasswordEncoder encoder;
    private final UserService userService;
    private final AuthorityService authorityService;

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute User user,
                                   @RequestParam(value = "constrError", required = false) String constrError,
                                   Model model) {
        String errMessage = null;
        if (constrError != null) {
            errMessage = "Такой логин уже существует!";
        }
        model.addAttribute("errMessage", errMessage);
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute User user) {
                user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorityService.findByAuthority("ROLE_USER"));

        Optional<User> savedUser = userService.save(user);
        if (savedUser.isEmpty()) {
            return "redirect:/registration?constrError=true";
        }
        return "redirect:/login";
    }

}
