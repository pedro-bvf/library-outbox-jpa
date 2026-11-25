package com.library.loans.driven.repositories.mappers;

import com.library.loans.domain.Book;
import com.library.loans.domain.Loan;
import com.library.loans.driven.repositories.models.BookMO;
import com.library.loans.driven.repositories.models.LoanDetailsMO;
import com.library.loans.driven.repositories.models.LoanDetailsMOPk;
import com.library.loans.driven.repositories.models.LoanMO;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface LoanMapper {

  @Mapping(target = "books", source = ".", qualifiedByName = "mapLoanDetailsToBooks")
  Loan toDomain(LoanMO loanMO);

  Book toDomain(BookMO bookMO);

  @Mapping(target = "loanDetails", ignore = true)
  LoanMO toModel(Loan loan);

  BookMO map(Book book);

  @Named("mapLoanDetailsToBooks")
  default List<Book> mapLoanDetailsToBooks(LoanMO loanMO) {
    return loanMO.getLoanDetails().stream().map(ld -> toDomain(ld.getBook())).toList();
  }

  default List<LoanDetailsMO> mapBooksToLoanDetails(List<Book> books, LoanMO loanMO) {
    if (books == null) return null;
    return books.stream()
        .map(
            book -> {
              var bookmo = map(book);
              LoanDetailsMOPk pk = new LoanDetailsMOPk(loanMO.getId(), bookmo.getId());
              return new LoanDetailsMO(pk, loanMO, bookmo);
            })
        .collect(Collectors.toList());
  }
}
