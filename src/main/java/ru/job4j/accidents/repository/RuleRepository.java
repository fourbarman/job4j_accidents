package ru.job4j.accidents.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Rule;

/**
 * RuleRepository.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 26.04.2023.
 */
public interface RuleRepository extends CrudRepository<Rule, Integer> {
}
