package com.mirea.confectionery.services;

import com.mirea.confectionery.models.Product;
import com.mirea.confectionery.models.Recipient;
import com.mirea.confectionery.models.Role;
import com.mirea.confectionery.models.User;
import com.mirea.confectionery.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Сервис для работы с пользователями
 */
@Service
public class UserService implements UserDetailsService {
    /** Поле с репозиторием пользователей */
    @Autowired
    private UserRepository userRepository;

    /** Поле с шифратором */
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Добавление пользователя
     * @param user Новый пользователь
     * @return Результат добавления
     */
    public boolean addUser(User user) {
        User userDB = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (userDB != null)
            return false;
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (!user.getUsername().equals("admin"))
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        else
            user.setRoles(new HashSet<>(Arrays.asList(new Role(1L, "ROLE_USER"), new Role(2L, "ROLE_ADMIN"))));
        userRepository.save(user);
        return true;
    }

    /**
     * Авторизация пользователя по почте или логину
     * @param username Имя пользователя
     * @param email Почта пользователя
     * @param password Пароль пользователя
     * @return Результат авторизации
     */
    public User login(String username, String email, String password) {
        User user = userRepository.findByUsernameOrEmail(username, email);
        if (user == null)
            return null;
        if (bCryptPasswordEncoder.matches(password, user.getPassword()))
            return user;
        else return null;
    }

    /**
     * Поиск пользователя по логину
     * @param username Логин пользователя
     * @return Результат поиска
     * @throws UsernameNotFoundException Ошибка при неудачном поиске
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    /**
     * Обновление корзины пользователя
     * @param user Пользователь
     * @return Результат обновления
     */
    public User updateCart(User user) {
        User current = userRepository.findByUsername(user.getUsername());
        current.setCart(user.getCart());
        current.getCart().forEach(product -> product.setUsers(Stream.of(current).collect(Collectors.toSet())));
        return userRepository.save(current);
    }

    /**
     * Обработка покупки пользователем
     * @param user Пользователь
     * @return true
     */
    public boolean purchase(User user) {
        user.getCart().clear();
        updateCart(user);
        return true;
    }
}
