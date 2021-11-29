package com.mirea.confectionery.services;

import com.mirea.confectionery.models.Category;
import com.mirea.confectionery.models.Product;
import com.mirea.confectionery.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

/**
 * Класс тестирования сервиса работы с категориями
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CategoryServiceTest {
    /** Поле сервиса категорий */
    @Autowired
    private CategoryService categoryService;

    /** Поле репозитория категорий */
    @MockBean
    private CategoryRepository categoryRepository;

    /**
     * Тестирование добавления новой категории
     */
    @Test
    void addCategory() {
        Category category = new Category();
        category.setCategoryId(2);
        category.setCategoryName("bd");
        category.setProductList(Collections.singletonList(new Product("name", "pname", "bname", 1f, 10)));
        categoryService.addCategory(category);

        Mockito.when(categoryRepository.findAll()).thenReturn(Collections.singletonList(category));
    }

    /**
     * Тестирование вывода всех категорий
     */
    @Test
    void findAll() {
        Category category = new Category();
        category.setCategoryId(2);
        category.setCategoryName("bd");
        category.setProductList(Collections.singletonList(new Product("name", "pname", "bname", 1f, 10)));
        categoryService.addCategory(category);

        Mockito.when(categoryRepository.findAll()).thenReturn(Collections.singletonList(category));
    }
}
