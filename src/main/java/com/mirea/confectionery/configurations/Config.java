package com.mirea.confectionery.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Класс конфигурации репозиториев
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.mirea.confectionery.repositories")
public class Config {
}
