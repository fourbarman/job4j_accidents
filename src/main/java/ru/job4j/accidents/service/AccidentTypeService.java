package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.util.List;
import java.util.Optional;

/**
 * AccidentTypeService.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 03.04.2023.
 */
@Service
@AllArgsConstructor
public class AccidentTypeService {
    private final AccidentTypeRepository types;

    /**
     * Get all AccidentTypes.
     *
     * @return AccidentTypes list.
     */
    public List<AccidentType> getAllTypes() {
        return this.types.findAll();
    }

    /**
     * Find AccidentType by id.
     *
     * @param id AccidentType id.
     * @return Optional of AccidentType.
     */
    public Optional<AccidentType> findTypeById(int id) {
        return this.types.findById(id);
    }
}
