package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;

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
    private final AccidentMem accidentMem;

    public void create(Accident accident) {
        this.accidentMem.putAccident(accident);
    }

    public List<Accident> getAllAccidents() {
        return this.accidentMem.getAllAccidents();
    }

    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }

    public void updateAccident(Accident accident) {
        this.accidentMem.update(accident);
    }
}
