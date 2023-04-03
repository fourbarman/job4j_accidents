package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * AccidentTypeMem.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 03.04.2023.
 */
@Repository
public class AccidentTypeMem {
    AtomicInteger ids = new AtomicInteger(3);
    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>(
            Map.of(
                    1, new AccidentType(1, "Две машины"),
                    2, new AccidentType(2, "Машина и человек"),
                    3, new AccidentType(3, "Машина и велосипед")
            )
    );

    public List<AccidentType> getAllTypes() {
        return this.types.values().stream().toList();
    }

    public Optional<AccidentType> findTypeById(int id) {
        return Optional.ofNullable(this.types.get(id));
    }
}
