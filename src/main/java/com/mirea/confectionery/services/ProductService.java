package com.mirea.confectionery.services;

import com.mirea.confectionery.models.Product;
import com.mirea.confectionery.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Сервис для работы с продуктами
 */
@Service
public class ProductService {
    /** Поле с репозиторием продуктов */
    @Autowired
    private ProductRepository productRepository;

    /**
     * Добавление нового продукта
     * @param product Новый продукт
     * @return Результат добавления
     */
    public boolean addProduct(Product product) {
        if (productRepository.findByFullName(product.getFullName()) == null) {
            productRepository.save(product);
            return true;
        }
        return false;
    }

    /**
     * Получение всех продуктов
     * @return Список продуктов
     */
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * Получение списка продуктов, отсортированных по названию
     * @return Список продуктов
     */
    public List<String> findDistinctProductNames() {
        return productRepository.findDistinctByProductName().stream()
                .map(Product::getProductName)
                .collect(Collectors.toList());
    }

    /**
     * Получение списка продуктов, отсортированных по имени бренда
     * @return Список продуктов
     */
    public List<String> findDistinctBrandNames() {
        return productRepository.findDistinctByBrandName().stream()
                .map(Product::getBrandName)
                .collect(Collectors.toList());
    }

    /**
     * Поиск продукта по ID
     * @param id Идентификатор продукта
     * @return Продукт
     */
    public Product findById(Long id) {
        return productRepository.getOne(id);
    }

    /**
     * Обновление числа продукта на складе
     * @param id Идентификатор продукта
     * @param new_amount Новое число продукта
     * @return Результат обновления
     */
    public Product updateProductAmount(Long id, Integer new_amount) {
        Product product = productRepository.getOne(id);
        product.setAmount(new_amount);
        return productRepository.save(product);
    }

    /**
     * Обновление числа продуктов на складе
     * @param products Новый список продуктов
     * @return true
     */
    public boolean updateAmounts(Set<Product> products) {
        List<Product> old_product = findAll();
        products.forEach(product -> updateProductAmount(product.getId(), old_product.stream()
                        .filter(t -> t.getId().equals(product.getId()))
                        .findFirst().get()
                        .getAmount() - product.getQuantity()));
        return true;
    }
}
