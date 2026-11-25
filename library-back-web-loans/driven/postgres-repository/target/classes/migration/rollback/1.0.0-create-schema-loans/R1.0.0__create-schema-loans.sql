DROP TABLE public.loan_details;

DROP TABLE public.loan;

DROP TABLE public.customer;

DROP TABLE public.book;

DELETE FROM flyway_schema_history WHERE version = '1.0.0';
