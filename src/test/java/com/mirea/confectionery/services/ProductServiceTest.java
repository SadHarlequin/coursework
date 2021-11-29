package com.mirea.confectionery.services;

import com.mirea.confectionery.models.Product;
import com.mirea.confectionery.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

/**
 * Класс тестирования продуктового сервиса
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProductServiceTest {
    /** Поле продуктового сервиса */
    @Autowired
    private ProductService productService;

    /** Поле продуктового репозитория */
    @MockBean
    private ProductRepository productRepository;

    /**
     * Тестирование добавления нового продукта
     */
    @Test
    void addProduct() {
        Product product = new Product("name", "pname", "bname", 1f, 10);
        productService.addProduct(product);

        Mockito.when(productRepository.findAll()).thenReturn(Collections.singletonList(product));
    }

    /**
     * Тестирования вывода списка всех продуктов
     */
    @Test
    void findAll() {
        Product product = new Product("name", "pname", "bname", 1f, 10);
        productService.addProduct(product);

        Mockito.when(productRepository.findAll()).thenReturn(Collections.singletonList(product));
    }

    /**
     * Тестирование поиска продукта по ID
     */
    @Test
    void findById() {
        Product product = new Product("name", "pname", "bname", 1f, 10);
        productService.addProduct(product);

        Mockito.when(productRepository.getOne(product.getId())).thenReturn(product);
    }
}
