package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Optional;

/**
 * AccidentTypeJdbcTemplate.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 17.04.2023.
 */
@Repository
@AllArgsConstructor
public class AccidentTypeJdbcTemplate {
    private final JdbcTemplate jdbc;

    private static final String FIND_ALL_TYPES = """
            SELECT id, name
            FROM types
            """;
    private static final String FIND_TYPE_BY_ID = """
            SELECT id, name
            FROM types
            WHERE id = ?
            """;

    /**
     * Return all Types from DB.
     *
     * @return Type list.
     */
    public List<AccidentType> getAllTypes() {
        return jdbc.query(FIND_ALL_TYPES, new AccidentTypeMapper());
    }

    /**
     * Find type by id.
     *
     * @param id Type id.
     * @return Optional of Type.
     */
    public Optional<AccidentType> findTypeById(int id) {
        return Optional.ofNullable(jdbc.queryForObject(FIND_TYPE_BY_ID, new AccidentTypeMapper(), id));
    }
}
