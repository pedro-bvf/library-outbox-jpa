DELETE FROM loan_details;

DELETE FROM loan;

DELETE FROM book;

DELETE FROM customer;

DELETE FROM flyway_schema_history WHERE version = '1.0.1';
