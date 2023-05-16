package ru.job4j.accidents.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.UserRepository;

/**
 * UserValidator.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 16.05.2023.
 */
@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {
    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            errors.rejectValue("username", "", "Пользователь с таким логином уже существует");
        }
    }
}
