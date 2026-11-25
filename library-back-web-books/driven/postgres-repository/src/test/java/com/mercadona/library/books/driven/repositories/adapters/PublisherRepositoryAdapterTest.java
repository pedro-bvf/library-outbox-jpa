package com.mercadona.library.books.driven.repositories.adapters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.mercadona.library.books.domain.PublisherDTO;
import com.mercadona.library.books.driven.repositories.mappers.PublisherRepositoryMapper;
import com.mercadona.library.books.driven.repositories.models.PublisherMO;
import com.mercadona.library.books.driven.repositories.repository.PublisherMOJpaRepository;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PublisherRepositoryAdapterTest {

  @Mock private PublisherMOJpaRepository publisherRepository;

  @Mock private PublisherRepositoryMapper publisherMapper;

  @InjectMocks private PublisherRepositoryAdapter publisherRepositoryAdapter;

  @Test
  void findById_returnsMappedPublisherWhenItExists() {
    var publisherId = UUID.randomUUID();
    var publisherMO = new PublisherMO();
    publisherMO.setId(publisherId);
    var publisherDTO = new PublisherDTO(publisherId, 1L, "Name", "Address");
    when(publisherRepository.findById(publisherId)).thenReturn(Optional.of(publisherMO));
    when(publisherMapper.toPublisherDTO(publisherMO)).thenReturn(publisherDTO);

    var result = publisherRepositoryAdapter.findById(publisherId);

    assertThat(result).contains(publisherDTO);
  }

  @Test
  void findById_returnsEmptyWhenPublisherDoesNotExist() {
    var publisherId = UUID.randomUUID();
    when(publisherRepository.findById(publisherId)).thenReturn(Optional.empty());

    var result = publisherRepositoryAdapter.findById(publisherId);

    assertThat(result).isEmpty();
  }
}
