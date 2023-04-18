package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Optional;

/**
 * RulesJdbcTemplate.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 17.04.2023.
 */
@Repository
@AllArgsConstructor
public class RulesJdbcTemplate {
    private final JdbcTemplate jdbc;

    private static final String FIND_ALL_RULES = """
            SELECT id, name
            FROM rules
            """;
    private static final String FIND_RULE_BY_ID = """
            SELECT id, name
            FROM rules
            WHERE id = ?
            """;

    /**
     * Get all Rules from DB.
     *
     * @return Rules list.
     */
    public List<Rule> getAllRules() {
        return jdbc.query(FIND_ALL_RULES, new RuleMapper());
    }

    /**
     * Find Rule by id.
     *
     * @param id Rule id.
     * @return Optional of Rule.
     */
    public Optional<Rule> findRuleById(int id) {
        return Optional.ofNullable(jdbc.queryForObject(FIND_RULE_BY_ID, new RuleMapper(), id));
    }
}
