CREATE TABLE IF NOT EXISTS book
(
    id           uuid        NOT NULL,
    public_id    bigint      NOT NULL,
    available    boolean     NOT NULL DEFAULT true,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS customer
(
    id             uuid    NOT NULL,
    public_id      bigint      NOT NULL,
    name           varchar,
    address        varchar,
    phone          numeric,
    birthday       date,
    card_image_url varchar,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS loan
(
    id                      uuid        NOT NULL,
    customer_id             uuid        NOT NULL,
    public_id               bigint      NOT NULL,
    booking_date            timestamptz,
    booking_expiration_date date,
    returned_date           timestamptz,
    created_by               varchar,
    created_date               timestamptz,
    modified_by             varchar,
    modified_date             timestamptz,
    PRIMARY KEY (id),
    CONSTRAINT FK_customer_TO_loan
        FOREIGN KEY (customer_id)
            REFERENCES customer (id)
);

CREATE TABLE IF NOT EXISTS loan_details
(
    loan_id     uuid        NOT NULL,
    book_id     uuid        NOT NULL,
    created_by   varchar,
    created_date   timestamptz,
    modified_by varchar,
    modified_date timestamptz,
    CONSTRAINT FK_loan_TO_loan_details
        FOREIGN KEY (loan_id)
            REFERENCES loan (id),
    CONSTRAINT FK_book_TO_loan_details
        FOREIGN KEY (book_id)
            REFERENCES book (id)
);
