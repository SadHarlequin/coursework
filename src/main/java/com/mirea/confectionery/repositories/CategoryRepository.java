package com.mirea.confectionery.repositories;

import com.mirea.confectionery.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс для работы с таблицей Категорий
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /**
     * Поиск по имени категории
     * @param categoryName Имя категории
     * @return Категория
     */
    Category findByCategoryName(String categoryName);
}
