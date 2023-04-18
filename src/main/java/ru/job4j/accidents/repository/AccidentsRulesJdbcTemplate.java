package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;

import java.util.ArrayList;
import java.util.List;

/**
 * AccidentsRulesJdbcTemplate.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 17.04.2023.
 */
@Repository
@AllArgsConstructor
public class AccidentsRulesJdbcTemplate {
    private final JdbcTemplate jdbc;

    private static final String FIND_RULES_BY_ACCIDENT_ID = """
            SELECT r.id, r.name
            FROM rules r 
            JOIN accidents_rules ar
            ON ar.rules_id = r.id 
            WHERE ar.accident_id = ? 
            """;
    private static final String INSERT_ACCIDENTS_RULES = """
            INSERT INTO accidents_rules(accident_id, rules_id) 
            VALUES (?, ?)
            """;
    private static final String DELETE_ACCIDENTS_RULES = """
            DELETE FROM accidents_rules
            WHERE id IN (SELECT id FROM accidents_rules WHERE accident_id = ?)
            """;

    /**
     * Find rules by accident id.
     *
     * @param accidentId Accident id.
     * @return Rules list.
     */
    public List<Rule> findRulesByAccidentId(int accidentId) {
        return jdbc.query(FIND_RULES_BY_ACCIDENT_ID, new RuleMapper(), accidentId);
    }

    /**
     * Write rules to DB.
     *
     * @param accident Accident.
     */
    public void createAccidentsRules(Accident accident) {
        List<Object[]> batchArgs = new ArrayList<>();
        for (Rule rule : accident.getRules()) {
            Object[] arr = {accident.getId(), rule.getId()};
            batchArgs.add(arr);
        }
        jdbc.batchUpdate(INSERT_ACCIDENTS_RULES, batchArgs);
    }

    /**
     * Update accidents_rules table with "Accident - Set<Rule>" relationship.
     *
     * @param accident Accident
     */
    public void updateAccidentsRules(Accident accident) {
        jdbc.update(DELETE_ACCIDENTS_RULES, accident.getId());
        this.createAccidentsRules(accident);
    }
}
