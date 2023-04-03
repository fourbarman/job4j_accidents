package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RulesMem;

import java.util.List;
import java.util.Optional;
/**
 * RulesService.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 03.04.2023.
 */
@Service
@AllArgsConstructor
public class RulesService {
    private final RulesMem rules;

    public List<Rule> getAllRules() {
        return this.rules.getAllRules();
    }

    public Optional<Rule> findRuleById(int id) {
        return this.rules.findRuleById(id);
    }
}
