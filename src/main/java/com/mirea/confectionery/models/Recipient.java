package com.mirea.confectionery.models;

import lombok.*;

import java.util.List;

/**
 * Класс получателя
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Recipient {
    /** Поле имени получателя */
    @NonNull
    private String firstName;

    /** Поле фамилии получателя */
    @NonNull
    private String lastName;

    /** Поле имени получателя */
    @NonNull
    private String email;

    /** Поле телефона получателя */
    @NonNull
    private String phoneNumber;

    /** Поле города получателя */
    @NonNull
    private String city;

    /** Поле адреса получателя */
    @NonNull
    private String address;

    /** Поле инструкций для получения получателя */
    private String instructions;

    /** Поле списка продуктов получателя */
    @NonNull
    private List<Product> productList;
}
