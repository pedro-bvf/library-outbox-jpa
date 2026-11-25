package com.library.books.driven.repositories.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableScheduling
@EntityScan(basePackages = {"com.library.books.driven.repositories.models"})
@EnableJpaRepositories(basePackages = {"com.library.books.driven.repositories"})
public class RepositoryConfig {
}
