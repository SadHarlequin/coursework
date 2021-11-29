package com.mirea.confectionery.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Класс работы с таблицей Категорий
 */
@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Category {
    /** Поле ID категории */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long categoryId;

    /** Поле имени категории */
    @NonNull
    @Column(name = "category_name")
    private String categoryName;

    /** Поле списка продуктов данной категории */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> productList;

}
