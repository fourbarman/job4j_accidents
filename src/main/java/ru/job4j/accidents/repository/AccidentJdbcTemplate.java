package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 * AccidentJdbcTemplate.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 17.04.2023.
 */
@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {
    final JdbcTemplate jdbc;
    private static final String GET_ALL_ACCIDENTS = """
            SELECT a.id, a.name, a.text, a.address, 
            t.id as type_id, t.name as type_name
            FROM accidents a 
            LEFT JOIN types t on a.type_id = t.id
            """;
    private static final String GET_ACCIDENT_BY_ID = """
            SELECT a.id, a.name, a.text, a.address, 
            t.id as type_id, t.name as type_name
            FROM accidents a 
            LEFT JOIN types t on a.type_id = t.id
            WHERE a.id = ?
            """;
    private static final String INSERT_ACCIDENT = """
            INSERT INTO accidents(name, text, address, type_id)
            VALUES (?, ?, ?, ?)
            """;
    private static final String UPDATE_ACCIDENT = """
            UPDATE accidents
            SET name = ?, text = ?, address = ?, type_id = ?
            WHERE id = ?
            """;

    /**
     * Write accident to DB.
     *
     * @param accident Accident.
     * @return Generated accident id.
     */
    public int create(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_ACCIDENT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        return (Integer) keyHolder.getKeys().get("id");
    }

    /**
     * Get all accidents from DB.
     *
     * @return Accidents list.
     */
    public List<Accident> getAllAccidents() {
        return jdbc.query(GET_ALL_ACCIDENTS, new AccidentMapper());
    }

    /**
     * Find accident by id.
     *
     * @param id Accident id.
     * @return Optional of accident.
     */
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(jdbc.queryForObject(GET_ACCIDENT_BY_ID, new AccidentMapper(), id));
    }

    /**
     * Update accident by id.
     *
     * @param accident Accident.
     * @return Optional of accident.
     */
    public Optional<Accident> update(Accident accident) {
        jdbc.update(UPDATE_ACCIDENT,
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId()
        );
        return Optional.of(accident);
    }
}
