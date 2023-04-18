package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RulesJdbcTemplate;
import ru.job4j.accidents.repository.RulesMem;

import java.util.*;
import java.util.stream.Collectors;

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
    private final RulesJdbcTemplate rules;

    /**
     * Get all Rules.
     *
     * @return Rule list.
     */
    public List<Rule> getAllRules() {
        return this.rules.getAllRules();
    }

    /**
     * Get Rules Set.
     *
     * @param rules String id array.
     * @return Rules Set.
     */
    public Set<Rule> getRulesSet(String[] rules) {
        int[] ruleIds = Arrays.stream(rules)
                .mapToInt(Integer::parseInt)
                .toArray();
        Set<Rule> accidentRules = new HashSet<>();
        for (int i : ruleIds) {
            accidentRules.add(this.rules.findRuleById(i).get());
        }
        return accidentRules;
    }
}
