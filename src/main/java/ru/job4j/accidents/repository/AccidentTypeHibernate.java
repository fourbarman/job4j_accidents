package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Optional;

/**
 * AccidentTypeHibernate.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 21.04.2023.
 */
@Repository
@AllArgsConstructor
public class AccidentTypeHibernate {
    private final SessionFactory sf;
    private static final String GET_ALL_TYPES = """
            FROM AccidentType t
            """;

    /**
     * Find AccidentType by id.
     *
     * @param id AccidentType id.
     * @return Optional of AccidentType.
     */
    public Optional<AccidentType> findTypeById(int id) {
        try (Session session = sf.openSession()) {
            return Optional.of(session.get(AccidentType.class, id));
        }
    }

    /**
     * Return all AccidentTypes.
     *
     * @return AccidentType list.
     */
    public List<AccidentType> getAllTypes() {
        try (Session session = sf.openSession()) {
            return session.createQuery(GET_ALL_TYPES, AccidentType.class).getResultList();
        }
    }
}
