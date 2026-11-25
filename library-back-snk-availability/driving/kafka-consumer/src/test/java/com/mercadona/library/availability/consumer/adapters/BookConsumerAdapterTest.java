package com.mercadona.library.availability.consumer.adapters;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.mercadona.fwk.sbo.library.book.BookAvailabilityEventKey;
import com.mercadona.fwk.sbo.library.book.BookAvailabilityEventValue;
import com.mercadona.library.availability.application.ports.driving.BookConsumerPort;
import com.mercadona.library.availability.consumer.mappers.BookEventMapperImpl;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BookConsumerAdapterTest {

  private BookConsumerAdapter consumerAdapter;
  private BookConsumerPort servicePort;

  private BookAvailabilityEventKey key;
  private BookAvailabilityEventValue value;

  @BeforeEach
  void before() {
    servicePort = Mockito.mock(BookConsumerPort.class);
    var mapper = new BookEventMapperImpl();

    consumerAdapter = new BookConsumerAdapter(servicePort, mapper);

    key = BookAvailabilityEventKey.newBuilder().setPublicId(99L).build();
    value = BookAvailabilityEventValue.newBuilder().setPublicId(99L).setAvailable(true).build();
  }

  @Test
  void shouldProcessValueEvent() {
    assertNotNull(consumerAdapter);

    ConsumerRecord<BookAvailabilityEventKey, BookAvailabilityEventValue> consumerRecord = Mockito.mock(ConsumerRecord.class);
    Mockito.when(consumerRecord.key()).thenReturn(key);
    Mockito.when(consumerRecord.value()).thenReturn(value);

    Assertions.assertDoesNotThrow(() -> consumerAdapter.consume(consumerRecord));
    Mockito.verify(servicePort).process(Mockito.any());
  }

  @Test
  void shouldDeleteOnTombstone() {
    ConsumerRecord<BookAvailabilityEventKey, BookAvailabilityEventValue> consumerRecord = Mockito.mock(ConsumerRecord.class);
    Mockito.when(consumerRecord.key()).thenReturn(key);
    Mockito.when(consumerRecord.value()).thenReturn(null);

    Assertions.assertDoesNotThrow(() -> consumerAdapter.consume(consumerRecord));
    Mockito.verify(servicePort).delete(99L);
  }
}
