package com.mercadona.library.availability.consumer.adapters;

import lombok.RequiredArgsConstructor;
import com.mercadona.fwk.sbo.library.book.BookAvailabilityEventKey;
import com.mercadona.fwk.sbo.library.book.BookAvailabilityEventValue;
import com.mercadona.library.availability.application.ports.driving.BookConsumerPort;
import com.mercadona.library.availability.consumer.mappers.BookEventMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookConsumerAdapter {
  private final BookConsumerPort service;
  private final BookEventMapper mapper;

  @KafkaListener(topics = "${books.kafka.consumer.topic}", groupId = "${books.kafka.consumer.groupId}")
  public void consume(ConsumerRecord<BookAvailabilityEventKey, BookAvailabilityEventValue> consumerRecord) {

    var publicId = consumerRecord.key().getPublicId();
    var eventValue = consumerRecord.value();

    if (eventValue != null) {
      var book = mapper.toBookDTO(eventValue);
      service.process(book);
    } else {
      service.delete(publicId);
    }
  }

}

