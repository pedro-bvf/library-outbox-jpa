package com.library.books.driven.repositories.adapters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.library.books.domain.AuthorDTO;
import com.library.books.domain.BookDTO;
import com.library.books.domain.PublisherDTO;
import com.library.books.driven.repositories.mappers.AuthorRepositoryMapper;
import com.library.books.driven.repositories.mappers.BookRepositoryMapper;
import com.library.books.driven.repositories.mappers.PublisherRepositoryMapper;
import com.library.books.driven.repositories.models.AuthorMO;
import com.library.books.driven.repositories.models.BookMO;
import com.library.books.driven.repositories.models.PublisherMO;
import com.library.books.driven.repositories.repository.BookMOJpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class BookRepositoryAdapterTest {

  @Mock private BookMOJpaRepository bookRepository;

  @Mock private BookRepositoryMapper bookMapper;

  @Mock private PublisherRepositoryMapper publisherMapper;

  @Mock private AuthorRepositoryMapper authorMapper;

  @InjectMocks private BookRepositoryAdapter bookRepositoryAdapter;

  private UUID bookId;
  private BookMO bookMO;
  private BookDTO bookDTO;

  @BeforeEach
  void setUp() {
    bookId = UUID.randomUUID();
    bookMO = new BookMO();
    bookMO.setId(bookId);
    bookDTO = new BookDTO(bookId, 1L, "Title", null, null, null, null, null, null, null, null, null);
  }

  @Test
  void findAll_mapsEachPageElementAndBuildsPageableFromArguments() {
    var pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
    var moPage = new PageImpl<>(List.of(bookMO), PageRequest.of(1, 5), 11);
    when(bookRepository.findAll(pageableCaptor.capture())).thenReturn(moPage);
    when(bookMapper.toBookDTO(bookMO)).thenReturn(bookDTO);

    var result = bookRepositoryAdapter.findAllBooks(2, 5, "+title");

    assertThat(result.data()).containsExactly(bookDTO);
    assertThat(result.pagination().requestedPage()).isEqualTo(2);
    assertThat(result.pagination().totalResults()).isEqualTo(11);
    assertThat(pageableCaptor.getValue().getPageNumber()).isEqualTo(1);
    assertThat(pageableCaptor.getValue().getPageSize()).isEqualTo(5);
  }

  @Test
  void findById_returnsMappedBookWhenItExists() {
    when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookMO));
    when(bookMapper.toBookDTO(bookMO)).thenReturn(bookDTO);

    var result = bookRepositoryAdapter.findBookById(bookId);

    assertThat(result).contains(bookDTO);
  }

  @Test
  void findById_returnsEmptyWhenBookDoesNotExist() {
    when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

    var result = bookRepositoryAdapter.findBookById(bookId);

    assertThat(result).isEmpty();
  }

  @Test
  void create_buildsPublisherAndAuthorReferencesAndSavesBook() {
    var publisherId = UUID.randomUUID();
    var authorId = UUID.randomUUID();
    var publisherDTO = new PublisherDTO(publisherId, null, null, null);
    var authorDTO = new AuthorDTO(authorId, null, null, null, null);
    var toCreate = new BookDTO(
      null, 1L, "Title", null, null, null, null, null, null, null,
      publisherDTO, Set.of(authorDTO));
    var publisherMO = new PublisherMO();
    publisherMO.setId(publisherId);
    var authorMO = new AuthorMO();
    authorMO.setId(authorId);
    var bookMOToSave = new BookMO();
    var savedMO = new BookMO();
    savedMO.setId(bookId);
    when(publisherMapper.toPublisherMO(publisherDTO)).thenReturn(publisherMO);
    when(authorMapper.toAuthorMO(authorDTO)).thenReturn(authorMO);
    when(bookMapper.toBookMO(toCreate)).thenReturn(bookMOToSave);
    when(bookRepository.save(bookMOToSave)).thenReturn(savedMO);
    when(bookMapper.toBookDTO(savedMO)).thenReturn(bookDTO);

    var result = bookRepositoryAdapter.createBook(toCreate);

    assertThat(result).isEqualTo(bookDTO);
    assertThat(bookMOToSave.getPublisher()).isSameAs(publisherMO);
    assertThat(bookMOToSave.getAuthors()).containsExactly(authorMO);
  }

  @Test
  void create_savesBookWithNoPublisherOrAuthorsWhenNoneProvided() {
    var toCreate = new BookDTO(
      null, 1L, "Title", null, null, null, null, null, null, null, null, null);
    var bookMOToSave = new BookMO();
    when(bookMapper.toBookMO(toCreate)).thenReturn(bookMOToSave);
    when(bookRepository.save(bookMOToSave)).thenReturn(bookMOToSave);
    when(bookMapper.toBookDTO(bookMOToSave)).thenReturn(bookDTO);

    bookRepositoryAdapter.createBook(toCreate);

    assertThat(bookMOToSave.getPublisher()).isNull();
    assertThat(bookMOToSave.getAuthors()).isEmpty();
    verify(publisherMapper, never()).toPublisherMO(any());
    verify(authorMapper, never()).toAuthorMO(any());
  }

  @Test
  void update_mapsSavesAndReturnsUpdatedBookWhenItExists() {
    var savedMO = new BookMO();
    savedMO.setId(bookId);
    when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookMO));
    when(bookRepository.save(bookMO)).thenReturn(savedMO);
    when(bookMapper.toBookDTO(savedMO)).thenReturn(bookDTO);

    var result = bookRepositoryAdapter.updateBook(bookId, bookDTO);

    assertThat(result).contains(bookDTO);
    verify(bookMapper).updateBookMOFromDTO(bookDTO, bookMO);
    verify(bookRepository).save(bookMO);
  }

  @Test
  void update_returnsEmptyAndNeverSavesWhenBookDoesNotExist() {
    when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

    var result = bookRepositoryAdapter.updateBook(bookId, bookDTO);

    assertThat(result).isEmpty();
    verify(bookRepository, never()).save(any());
  }

  @Test
  void deleteById_delegatesToJpaRepository() {
    bookRepositoryAdapter.deleteBookById(bookId);

    verify(bookRepository).deleteById(bookId);
  }

  @Test
  void findByPublicIds_returnsMappedBooksForGivenPublicIds() {
    var publicIds = List.of(1L, 2L);
    when(bookRepository.findByPublicIdIn(publicIds)).thenReturn(List.of(bookMO));
    when(bookMapper.toBookDTO(bookMO)).thenReturn(bookDTO);

    var result = bookRepositoryAdapter.findByPublicIds(publicIds);

    assertThat(result).containsExactly(bookDTO);
  }

  @Test
  void findByPublicIds_returnsEmptyListWithoutQueryingWhenInputIsNullOrEmpty() {
    assertThat(bookRepositoryAdapter.findByPublicIds(null)).isEmpty();
    assertThat(bookRepositoryAdapter.findByPublicIds(List.of())).isEmpty();

    verify(bookRepository, never()).findByPublicIdIn(any());
  }
}
