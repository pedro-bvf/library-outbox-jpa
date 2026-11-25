DROP TABLE public.author_book;

DROP TABLE public.book;

DROP TABLE public.publisher;

DROP TABLE public.author;

DELETE FROM flyway_schema_history WHERE version = '1.0.0';
