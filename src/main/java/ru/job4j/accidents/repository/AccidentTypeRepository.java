package ru.job4j.accidents.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.AccidentType;

/**
 * AccidentTypeRepository.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 26.04.2023.
 */
public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
}
