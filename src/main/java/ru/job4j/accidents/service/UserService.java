package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.UserRepository;

import java.sql.SQLException;
import java.util.Optional;

/**
 * UserService.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 15.05.2023.
 */
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found.");
        }
        return (UserDetails) user.get();
    }

    public Optional<User> save(User user) {
        User savedUser = null;
        try {
            savedUser = userRepository.save(user);
        } catch (DataAccessException e) {
            System.out.println(e.getCause().toString());
        }
        return Optional.ofNullable(savedUser);
    }
}
