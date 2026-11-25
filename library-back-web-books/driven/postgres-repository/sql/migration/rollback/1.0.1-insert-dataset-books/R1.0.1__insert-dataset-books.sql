DELETE FROM author_book;

DELETE FROM book;

DELETE FROM author;

DELETE FROM publisher;

DELETE FROM flyway_schema_history WHERE version = '1.0.1';
