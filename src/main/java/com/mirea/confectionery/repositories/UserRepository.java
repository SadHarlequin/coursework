package com.mirea.confectionery.repositories;

import com.mirea.confectionery.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс для работы с таблицей Пользователей
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Поиск пользователя по логину
     * @param username Логин пользователя
     * @return Пользователь
     */
    User findByUsername(String username);

    /**
     * Поиск пользователя по почте
     * @param email Почта пользователя
     * @return Пользователь
     */
    User findByEmail(String email);

    /**
     * Поиск пользователя по логину или по почте
     * @param username Логин пользователя
     * @param email Почта пользователя
     * @return Пользователь
     */
    User findByUsernameOrEmail(String username, String email);
}
