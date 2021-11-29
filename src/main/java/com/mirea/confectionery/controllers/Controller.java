package com.mirea.confectionery.controllers;

import com.mirea.confectionery.models.Category;
import com.mirea.confectionery.models.Product;
import com.mirea.confectionery.models.Recipient;
import com.mirea.confectionery.models.User;
import com.mirea.confectionery.services.CategoryService;
import com.mirea.confectionery.services.ProductService;
import com.mirea.confectionery.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для работы с фронтом
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {
    /** Поле сервиса для работы с пользователями */
    @Autowired
    private UserService userService;
    /** Поле сервиса для работы с продуктами */
    @Autowired
    private ProductService productService;
    /** Поле сервиса для работы с категориями */
    @Autowired
    private CategoryService categoryService;


    /**
     * Создание пользователя
     * @param user Форма пользователя
     */
    @PostMapping("/users")
    void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    /**
     * Логин пользователя
     * @param username Имя пользователя
     * @param email Почта пользователя
     * @param password Пароль пользователя
     * @return Экземпляр пользователя
     */
    @GetMapping("/users")
    User login(String username, String email, String password) {
        return userService.login(username, email, password);
    }

    /**
     * Метод получения всех продуктов
     * @return Список всех продуктов
     */
    @GetMapping("/products")
    List<Product> findAllProducts() {
        return productService.findAll();
    }

    /**
     * Поиск продукта по его id
     * @param id Идентификатор продукта
     * @return Найденный продукт
     */
    @GetMapping("/products/{id}")
    Product findProductById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    /**
     * Сортировка продуктов по именам
     * @return Отсортированный список продуктов по именам
     */
    @GetMapping("/products/names")
    List<String> findDistinctProductNames() {
        return productService.findDistinctProductNames();
    }

    /**
     * Сортировка продуктов по имени бренда
     * @return Отсортированный список продуктов по именам брендов
     */
    @GetMapping("/products/brands")
    List<String> findDistinctBrandNames() {
        return productService.findDistinctBrandNames();
    }

    /**
     * Метод получения всех категорий
     * @return Список всех категорий
     */
    @GetMapping("/categories")
    List<Category> findAllCategories() {
        return categoryService.findAll();
    }

    /**
     * Метод оплаты корзины пользователя
     * @param user Пользователь, совершающий оплату
     * @return Результат покупки
     */
    @PostMapping("/payment/user")
    boolean purchase(@RequestBody User user){
        productService.updateAmounts(user.getCart());
        return userService.purchase(user);
    }


    /**
     * Обновление корзины пользователя
     * @param user Пользователь, совершающий покупки
     * @return Обновленный экземпляр пользователя
     */
    @PostMapping("/cart/update")
    User updateCart(@RequestBody User user){
        return userService.updateCart(user);
    }
}
