DROP TABLE public.book_availability_outbox;

DELETE FROM flyway_schema_history WHERE version = '1.0.2';
