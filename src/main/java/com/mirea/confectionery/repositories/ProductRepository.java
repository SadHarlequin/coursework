package com.mirea.confectionery.repositories;

import com.mirea.confectionery.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Интерфейс для работы с таблицей Продуктов
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Поиск продукта по полному названию
     * @param fullName Полное название продукта
     * @return Продукт
     */
    Product findByFullName(String fullName);

    /**
     * Получение списка продуктов, отсортированных по названию
     * @return Список продуктов
     */
    @Query(value = "SELECT DISTINCT on (p.product_name) p.* FROM products p", nativeQuery = true)
    List<Product> findDistinctByProductName();

    /**
     * Получение списка продуктов, отсортированных по имени бренда
     * @return Список продуктов
     */
    @Query(value = "SELECT DISTINCT on (p.brand_name) p.* FROM products p", nativeQuery = true)
    List<Product> findDistinctByBrandName();
}
