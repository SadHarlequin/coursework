package com.mirea.confectionery.configurations;

import com.mirea.confectionery.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Класс конфигурации безопасности
 */
@Configuration
@EnableConfigurationProperties
public class SecureConfig extends WebSecurityConfigurerAdapter {
    /** Поле сервиса для работы с пользователями */
    @Autowired
    private UserService userService;

    /**
     * Метод конфигурации защиты с http
     * @param http модифицируемый экземпляр HttpSecurity
     * @throws Exception в случае возникновения ошибки
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
    }

    /**
     * Метод конфигурации защиты с аутентификацией
     * @param auth модифицируемый экземпляр AuthenticationManagerBuilder
     * @throws Exception в случае возникновения ошибки
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    /**
     * Бин создания BCrypt шифровщика
     * @return Экземпляр шифровщика
     */
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
