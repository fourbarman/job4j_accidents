package ru.job4j.accidents.model;

import lombok.*;

import javax.persistence.*;
/**
 * User.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 15.05.2023.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String username;
    String password;
    boolean enabled;
    @ManyToOne
    @JoinColumn(name = "authority_id")
    Authority authority;
}
