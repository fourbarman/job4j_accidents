package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Optional;

/**
 * RulesHibernate.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 21.04.2023.
 */
@Repository
@AllArgsConstructor
public class RulesHibernate {
    private final SessionFactory sf;
    private static final String GET_ALL_RULES = """
            FROM Rule
            """;

    /**
     * Find Rule by id.
     *
     * @param id Rule id.
     * @return Optional of Rule.
     */
    public Optional<Rule> findRuleById(int id) {
        try (Session session = sf.openSession()) {
            return Optional.of(session.get(Rule.class, id));
        }
    }

    /**
     * Get all Rules.
     *
     * @return Rules list.
     */
    public List<Rule> getAllRules() {
        try (Session session = sf.openSession()) {
            return session.createQuery(GET_ALL_RULES, Rule.class).getResultList();
        }
    }
}
