package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.service.AuthorityService;
import ru.job4j.accidents.service.UserService;
import ru.job4j.accidents.util.UserValidator;


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
    private final UserValidator userValidator;

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute User user) {
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute @Validated User user,
                           BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors()) {
            return "/registration";
        }
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorityService.findByAuthority("ROLE_USER"));
        userService.save(user);
        return "redirect:/login";
    }
}
