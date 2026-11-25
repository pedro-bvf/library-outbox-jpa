package com.mercadona.library.loans.driven.repositories.mappers;

import com.mercadona.library.loans.domain.Book;
import com.mercadona.library.loans.domain.Customer;
import com.mercadona.library.loans.domain.Loan;
import com.mercadona.library.loans.driven.repositories.models.BookMO;
import com.mercadona.library.loans.driven.repositories.models.CustomerMO;
import com.mercadona.library.loans.driven.repositories.models.LoanMO;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-11-25T15:18:32+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.5.1 (Eclipse Adoptium)"
)
@Component
public class LoanMapperImpl implements LoanMapper {

    @Override
    public Loan toDomain(LoanMO loanMO) {
        if ( loanMO == null ) {
            return null;
        }

        List<Book> books = null;
        UUID id = null;
        Long publicId = null;
        Customer customer = null;
        OffsetDateTime bookingDate = null;
        LocalDate bookingExpirationDate = null;
        OffsetDateTime returnedDate = null;

        books = mapLoanDetailsToBooks( loanMO );
        id = loanMO.getId();
        publicId = loanMO.getPublicId();
        customer = customerMOToCustomer( loanMO.getCustomer() );
        bookingDate = loanMO.getBookingDate();
        bookingExpirationDate = loanMO.getBookingExpirationDate();
        returnedDate = loanMO.getReturnedDate();

        Loan loan = new Loan( id, publicId, customer, bookingDate, bookingExpirationDate, returnedDate, books );

        return loan;
    }

    @Override
    public Book toDomain(BookMO bookMO) {
        if ( bookMO == null ) {
            return null;
        }

        UUID id = null;
        Long publicId = null;

        id = bookMO.getId();
        publicId = bookMO.getPublicId();

        Book book = new Book( id, publicId );

        return book;
    }

    @Override
    public LoanMO toModel(Loan loan) {
        if ( loan == null ) {
            return null;
        }

        LoanMO loanMO = new LoanMO();

        loanMO.setId( loan.id() );
        loanMO.setCustomer( customerToCustomerMO( loan.customer() ) );
        loanMO.setPublicId( loan.publicId() );
        loanMO.setBookingDate( loan.bookingDate() );
        loanMO.setBookingExpirationDate( loan.bookingExpirationDate() );
        loanMO.setReturnedDate( loan.returnedDate() );

        return loanMO;
    }

    @Override
    public BookMO map(Book book) {
        if ( book == null ) {
            return null;
        }

        BookMO bookMO = new BookMO();

        bookMO.setId( book.id() );
        bookMO.setPublicId( book.publicId() );

        return bookMO;
    }

    protected Customer customerMOToCustomer(CustomerMO customerMO) {
        if ( customerMO == null ) {
            return null;
        }

        UUID id = null;
        Long publicId = null;
        String name = null;
        String address = null;
        Long phone = null;
        LocalDate birthday = null;
        String cardImageUrl = null;

        id = customerMO.getId();
        publicId = customerMO.getPublicId();
        name = customerMO.getName();
        address = customerMO.getAddress();
        phone = customerMO.getPhone();
        birthday = customerMO.getBirthday();
        cardImageUrl = customerMO.getCardImageUrl();

        Customer customer = new Customer( id, publicId, name, address, phone, birthday, cardImageUrl );

        return customer;
    }

    protected CustomerMO customerToCustomerMO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerMO customerMO = new CustomerMO();

        customerMO.setId( customer.id() );
        customerMO.setPublicId( customer.publicId() );
        customerMO.setName( customer.name() );
        customerMO.setAddress( customer.address() );
        customerMO.setPhone( customer.phone() );
        customerMO.setBirthday( customer.birthday() );
        customerMO.setCardImageUrl( customer.cardImageUrl() );

        return customerMO;
    }
}
