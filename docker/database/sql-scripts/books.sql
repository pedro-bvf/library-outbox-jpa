CREATE TABLE author
(
    id        uuid    NOT NULL,
    public_id bigint      NOT NULL,
    name      varchar,
    birthday  date   ,
    url_image varchar,
    PRIMARY KEY (id)
);

CREATE TABLE author_book
(
    author_id uuid NOT NULL,
    book_id uuid NOT NULL
);

CREATE TABLE book
(
    id           uuid        NOT NULL,
    public_id    bigint      NOT NULL,
    publisher_id uuid        NOT NULL,
    title        varchar    ,
    cover_url    varchar    ,
    edition      varchar    ,
    price        decimal    ,
    languages    varchar    ,
    epub_url     varchar    ,
    is_digital   boolean    ,
    is_deleted   boolean    ,
    created_by   varchar,
    created_date   timestamptz,
    modified_by varchar,
    modified_date timestamptz,
    PRIMARY KEY (id)
);

CREATE TABLE publisher
(
    id              uuid    NOT NULL,
    public_id       bigint,
    name            varchar,
    address         varchar,
    PRIMARY KEY (id)
);

ALTER TABLE book
    ADD CONSTRAINT FK_publisher_TO_book
        FOREIGN KEY (publisher_id)
            REFERENCES publisher (id);

ALTER TABLE book
    ADD CONSTRAINT uk_book_public_id UNIQUE (public_id);

ALTER TABLE author_book
    ADD CONSTRAINT FK_author_TO_author_book
        FOREIGN KEY (author_id)
            REFERENCES author (id);

ALTER TABLE author_book
    ADD CONSTRAINT FK_book_TO_author_book
        FOREIGN KEY (book_id)
            REFERENCES book (id);