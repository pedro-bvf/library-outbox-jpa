package com.mercadona.library.loans.driven.repositories.config;

import java.time.OffsetDateTime;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// Plain Spring Data JPA auditing config: enables @EnableJpaAuditing so AuditableEntity's
// @CreatedBy/@CreatedDate/@LastModifiedBy/@LastModifiedDate fields actually get populated.
// No fwkcna-starter-jpa-auditing involved anymore - this course variant has no authentication,
// so the auditor is a fixed "system" value. The date/time provider is needed because Spring
// Data's default only emits LocalDateTime/Instant/etc, which can't convert into the
// OffsetDateTime fields on AuditableEntity.
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware", dateTimeProviderRef = "auditingDateTimeProvider")
public class JpaAuditingConfig {

  @Bean
  public AuditorAware<String> auditorAware() {
    return () -> Optional.of("system");
  }

  @Bean
  public DateTimeProvider auditingDateTimeProvider() {
    return () -> Optional.of(OffsetDateTime.now());
  }
}
