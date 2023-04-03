package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * AccidentMem.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 28.03.2023.
 */
@Repository
@AllArgsConstructor
public class AccidentMem {
    private final AtomicInteger ids = new AtomicInteger(3);
    private final Map<Integer, Accident> store = new ConcurrentHashMap<>(
            Map.of(1, new Accident(1, "Accident1", "Accident1Text", "Accident1Address", null, null),
                    2, new Accident(2, "Accident2", "Accident2Text", "Accident2Address", null, null),
                    3, new Accident(3, "Accident3", "Accident3Text", "Accident3Address", null, null))
    );

    public void putAccident(Accident accident) {
        accident.setId(ids.incrementAndGet());
        this.store.putIfAbsent(accident.getId(), accident);
    }

    public List<Accident> getAllAccidents() {
        return store.values().stream().toList();
    }

    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(this.store.get(id));
    }

    public void update(Accident accident) {
        this.store.computeIfPresent(accident.getId(), (k, v) -> accident);
    }
}
