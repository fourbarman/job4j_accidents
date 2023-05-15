package ru.job4j.accidents.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.User;

import java.util.Optional;

/**
 * UserRepository.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 15.05.2023.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findUserByUsername(String username);
}
