package com.mercadona.library.loans.driving.controllers.mappers;

import com.mercadona.library.api.web_loans.model.CustomerResource;
import com.mercadona.library.api.web_loans.model.LoanResource;
import com.mercadona.library.api.web_loans.model.LoanResourceRequest;
import com.mercadona.library.api.web_loans.model.LoanResourceResponse;
import com.mercadona.library.loans.domain.Book;
import com.mercadona.library.loans.domain.Customer;
import com.mercadona.library.loans.domain.Loan;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-11-25T15:18:30+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.5.1 (Eclipse Adoptium)"
)
@Component
public class LoansDTOMapperImpl implements LoansDTOMapper {

    @Override
    public LoanResourceResponse toLoanResourceResponse(Loan loan) {
        if ( loan == null ) {
            return null;
        }

        LoanResourceResponse loanResourceResponse = new LoanResourceResponse();

        loanResourceResponse.setData( fromDomain( loan ) );

        return loanResourceResponse;
    }

    @Override
    public LoanResource fromDomain(Loan loan) {
        if ( loan == null ) {
            return null;
        }

        LoanResource loanResource = new LoanResource();

        loanResource.setId( loan.id() );
        loanResource.setPublicId( loan.publicId() );
        loanResource.setCustomer( customerToCustomerResource( loan.customer() ) );
        loanResource.setBooks( mapBooksToIds( loan.books() ) );
        loanResource.setBookingDate( loan.bookingDate() );
        loanResource.setBookingExpirationDate( loan.bookingExpirationDate() );
        loanResource.setReturnedDate( loan.returnedDate() );

        return loanResource;
    }

    @Override
    public Loan fromLoanResourceRequest(LoanResourceRequest request) {
        if ( request == null ) {
            return null;
        }

        Customer customer = null;
        List<Book> books = null;

        customer = loanResourceRequestToCustomer( request );
        books = mapBookIds( request.getBookIds() );

        UUID id = null;
        Long publicId = null;
        OffsetDateTime bookingDate = null;
        LocalDate bookingExpirationDate = null;
        OffsetDateTime returnedDate = null;

        Loan loan = new Loan( id, publicId, customer, bookingDate, bookingExpirationDate, returnedDate, books );

        return loan;
    }

    protected CustomerResource customerToCustomerResource(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerResource customerResource = new CustomerResource();

        customerResource.setId( customer.id() );
        customerResource.setPublicId( customer.publicId() );
        customerResource.setName( customer.name() );
        customerResource.setBirthday( customer.birthday() );
        customerResource.setAddress( customer.address() );
        if ( customer.phone() != null ) {
            customerResource.setPhone( customer.phone().intValue() );
        }
        customerResource.setCardImageUrl( customer.cardImageUrl() );

        return customerResource;
    }

    protected Customer loanResourceRequestToCustomer(LoanResourceRequest loanResourceRequest) {
        if ( loanResourceRequest == null ) {
            return null;
        }

        UUID id = null;

        id = loanResourceRequest.getCustomerId();

        Long publicId = null;
        String name = null;
        String address = null;
        Long phone = null;
        LocalDate birthday = null;
        String cardImageUrl = null;

        Customer customer = new Customer( id, publicId, name, address, phone, birthday, cardImageUrl );

        return customer;
    }
}
