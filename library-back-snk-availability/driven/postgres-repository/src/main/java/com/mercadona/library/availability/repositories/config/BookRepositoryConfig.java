package com.mercadona.library.availability.repositories.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.mercadona.library.availability.repositories"})
public class BookRepositoryConfig {
}
