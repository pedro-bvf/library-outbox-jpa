package com.mercadona.library.availability.consumer.adapters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
class KafkaConsumerConfig {

  @Bean
  DefaultErrorHandler kafkaErrorHandler() {
    // One initial attempt and four retries before logging the failed record.
    return new DefaultErrorHandler(new FixedBackOff(1000L, 4L));
  }
}
