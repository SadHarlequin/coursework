package com.mirea.confectionery.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/**
 * Класс работы с таблицей Ролей
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Role implements GrantedAuthority {
    /** Поле ID роли */
    @Id
    @Column(name = "role_id")
    @NonNull
    private Long id;

    /** Поле названия роли */
    @Column(name = "role_name")
    @NonNull
    private String name;

    /** Поле списка пользователей с данной ролью */
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users;

    /**
     * Получение представления полномочий объекта
     * @return Имя роли
     */
    @Override
    public String getAuthority() {
        return getName();
    }
}
