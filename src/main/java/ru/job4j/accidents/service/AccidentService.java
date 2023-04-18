package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentJdbcTemplate;
import ru.job4j.accidents.repository.AccidentTypeJdbcTemplate;
import ru.job4j.accidents.repository.AccidentsRulesJdbcTemplate;

import java.util.HashSet;
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
    private final AccidentJdbcTemplate accidentRepository;
    private final AccidentTypeJdbcTemplate typeRepository;
    private final AccidentsRulesJdbcTemplate accidentsRulesRepository;

    /**
     * Create Accident.
     *
     * @param accident Accident.
     */
    public void create(Accident accident) {
        int id = this.accidentRepository.create(accident);
        if (id != 0) {
            accident.setId(id);
            accidentsRulesRepository.createAccidentsRules(accident);
        }
    }

    /**
     * Get all Accidents.
     *
     * @return Accident list.
     */
    public List<Accident> getAllAccidents() {
        List<Accident> accidents = accidentRepository.getAllAccidents();
        for (Accident a : accidents) {
            this.buildAccident(a);
        }
        return accidents;
    }

    /**
     * Find accident by id.
     *
     * @param id Accident id.
     * @return Optional of Accident.
     */
    public Optional<Accident> findById(int id) {
        Optional<Accident> found = this.accidentRepository.findById(id);
        found.ifPresent(this::buildAccident);
        return found;
    }

    /**
     * Update Accident.
     * Insert accident then update accident-rules relationship.
     *
     * @param accident Accident.
     */
    @Transactional
    public void updateAccident(Accident accident) {
        this.accidentRepository.update(accident);
        this.accidentsRulesRepository.updateAccidentsRules(accident);
    }

    /**
     * Build Accident.
     * Helper method.
     * Find Type, Rules and set them to Accident.
     *
     * @param accident Accident.
     */
    private void buildAccident(Accident accident) {
        accident.setType(
                typeRepository.findTypeById(accident.getType().getId()).get()
        );
        accident.setRules(
                new HashSet<>(
                        accidentsRulesRepository.findRulesByAccidentId(accident.getId()))
        );
    }
}
