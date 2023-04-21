package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.*;

import java.util.List;
import java.util.Optional;

/**
 * AccidentService.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 28.03.2023.
 */
@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentHibernate accidentRepository;

    /**
     * Create Accident.
     *
     * @param accident Accident.
     */
    public void create(Accident accident) {
        this.accidentRepository.create(accident);
    }

    /**
     * Get all Accidents.
     *
     * @return Accident list.
     */
    public List<Accident> getAllAccidents() {
        return accidentRepository.getAllAccidents();
    }

    /**
     * Find accident by id.
     *
     * @param id Accident id.
     * @return Optional of Accident.
     */
    public Optional<Accident> findById(int id) {
        return this.accidentRepository.findById(id);

    }

    /**
     * Update Accident.
     * Insert accident then update accident-rules relationship.
     *
     * @param accident Accident.
     */
    public void updateAccident(Accident accident) {
        this.accidentRepository.update(accident);
    }
}
