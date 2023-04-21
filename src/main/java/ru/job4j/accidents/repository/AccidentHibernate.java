package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.QueryHints;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

/**
 * AccidentHibernate.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 21.04.2023.
 */
@Repository
@AllArgsConstructor
public class AccidentHibernate {
    private final SessionFactory sf;

    private static final String GET_ACCIDENT_BY_ID = """
            SELECT DISTINCT a 
            FROM Accident a
            LEFT JOIN FETCH a.type
            JOIN FETCH a.rules
            WHERE a.id = :fId
            """;
    private static final String GET_ALL_ACCIDENTS = """
            SELECT DISTINCT a 
            FROM Accident a
            LEFT JOIN FETCH a.type
            JOIN FETCH a.rules
            """;

    /**
     * Save Accident to DB.
     *
     * @param accident Accident.
     * @return Optional of accident.
     */
    public Optional<Accident> create(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(accident);
            session.getTransaction().commit();
            return Optional.of(accident);
        }
    }

    /**
     * Return Accident by id.
     *
     * @param id Accident id.
     * @return Optional of accident.
     */
    public Optional<Accident> findById(int id) {
        try (Session session = sf.openSession()) {
            Accident accident = session.createQuery(GET_ACCIDENT_BY_ID, Accident.class)
                    .setParameter("fId", id).getSingleResult();
            return Optional.of(accident);
        }
    }

    /**
     * Update Accident.
     *
     * @param accident Accident.
     * @return Optional of accident.
     */
    public Optional<Accident> update(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.merge(accident);
            session.getTransaction().commit();
            return Optional.of(accident);
        }
    }

    /**
     * Return all accidents.
     *
     * @return Accident list.
     */
    public List<Accident> getAllAccidents() {
        try (Session session = sf.openSession()) {
            return session.createQuery(GET_ALL_ACCIDENTS, Accident.class)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .getResultList();
        }
    }
}
