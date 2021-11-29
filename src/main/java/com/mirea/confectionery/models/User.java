package com.mirea.confectionery.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 * Класс для работы с таблицей Пользователей
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class User implements UserDetails {
    /** Поле ID пользователя */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    /** Поле логина пользователя */
    @NonNull
    @Column(name = "username")
    private String username;

    /** Поле пароля пользователя */
    @NonNull
    @Column(name = "password")
    private String password;

    /** Поле почты пользователя */
    @NonNull
    @Column(name = "email")
    private String email;

    /** Поле списка ролей пользователя */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    /** Поле корзины пользователя */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_products", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> cart;

    /**
     * Получение представления полномочий объекта
     * @return Список ролей
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    /**
     * Проверка истекшего аккаунта
     * @return true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Проверка блокировки аккаунта
     * @return true
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Проверка истечения реквизитов для входа
     * @return true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Проверка активации аккаунта
     * @return true
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
