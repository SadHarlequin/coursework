package com.mirea.confectionery.services;

import com.mirea.confectionery.models.Category;
import com.mirea.confectionery.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с категориями
 */
@Service
public class CategoryService {
    /** Поле репозитория с категориями */
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Добавление новой категории
     * @param category Новая категория
     * @return Результат добавления
     */
    public boolean addCategory(Category category){
        if (categoryRepository.findByCategoryName(category.getCategoryName()) == null) {
            categoryRepository.save(category);
            return true;
        }
        return false;
    }

    /**
     * Получение всех категорий
     * @return Список категорий
     */
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
}
