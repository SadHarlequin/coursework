package com.mirea.confectionery.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Класс работы с таблицей Продуктов
 */
@Entity
@Table(name = "products")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Product {
    /** Поле ID продукта */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    /** Поле полного названия продукта */
    @NonNull
    @Column(name = "full_name")
    private String fullName;

    /** Поле имени продукта */
    @NonNull
    @Column(name = "product_name")
    private String productName;

    /** Поле названия бренда продукта */
    @NonNull
    @Column(name = "brand_name")
    private String brandName;

    /** Поле стоимости продукта */
    @NonNull
    @Column(name = "price")
    private Float price;

    /** Поле числа единиц продукта на складе */
    @NonNull
    @Column(name = "amount")
    private Integer amount;

    /** Поле численности продукта в корзине */
    @NonNull
    @Column(name = "quantity")
    private Integer quantity = 1;

    /** Поле категории продукта */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category = new Category();

    /** Поле списка пользователей с данным продуктом */
    @ManyToMany(mappedBy = "cart")
    @JsonIgnore
    private Set<User> users;

    /** Поле списка ID пользователей с данным продуктом */
    @Transient
    private List<Long> userIds = new ArrayList<>();

    /**
     * Переопределяющий метод для описания объекта
     * @return Строка с необходимыми об объекте данными
     */
    @Override
    public String toString() {
        return String.format("Product: %s, quantity: %d, total cost: $%.2f", getFullName(), getQuantity(), getQuantity() * getPrice());
    }
}
