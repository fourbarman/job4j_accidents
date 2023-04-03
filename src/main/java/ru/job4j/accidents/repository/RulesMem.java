package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * RulesMem.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 03.04.2023.
 */
@Repository
@AllArgsConstructor
public class RulesMem {
    private final AtomicInteger ids = new AtomicInteger(3);

    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>(
            Map.of(
                    1, new Rule(1, "Статья. 1"),
                    2, new Rule(2, "Статья. 2"),
                    3, new Rule(3, "Статья. 3")
            )
    );

    public List<Rule> getAllRules() {
        return this.rules.values().stream().toList();
    }

    public Optional<Rule> findRuleById(int id) {
        return Optional.ofNullable(this.rules.get(id));
    }
}
