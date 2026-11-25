package com.mercadona.library.loans.driving.controllers.mappers;

import com.mercadona.library.api.web_loans.model.LoanResource;
import com.mercadona.library.api.web_loans.model.LoanResourceRequest;
import com.mercadona.library.api.web_loans.model.LoanResourceResponse;
import com.mercadona.library.loans.domain.Book;
import com.mercadona.library.loans.domain.Loan;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface LoansDTOMapper {
  @Mapping(target = "data", source = "loan")
  LoanResourceResponse toLoanResourceResponse(Loan loan);

  LoanResource fromDomain(Loan loan);

  @Mapping(target = "books", source = "request.bookIds", qualifiedByName = "mapBookIds")
  @Mapping(target = "customer.id", source = "request.customerId")
  Loan fromLoanResourceRequest(LoanResourceRequest request);

  @Named("mapBookIds")
  default List<Book> mapBookIds(List<UUID> bookIds) {
    if (bookIds == null) return new ArrayList<>();
    return bookIds.stream().map(id -> new Book(id, null)).toList();
  }

  default List<UUID> mapBooksToIds(List<Book> books) {
    if (books == null) return null;
    return books.stream().map(Book::id).collect(Collectors.toList());
  }
}
