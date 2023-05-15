package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Authority;
import ru.job4j.accidents.repository.AuthorityRepository;

/**
 * AuthorityService.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 15.05.2023.
 */
@Service
@AllArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    public Authority findByAuthority(String authority) {
        return authorityRepository.findByAuthority(authority);
    }
}
