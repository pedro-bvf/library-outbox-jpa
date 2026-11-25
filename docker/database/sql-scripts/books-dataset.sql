-- ===========================
-- Publishers
-- ===========================
INSERT INTO publisher (id, public_id, name, address)
VALUES
    ('11111111-1111-1111-1111-111111111111', 1, 'Editorial Alpha', 'Av. Central 123, CDMX'),
    ('22222222-2222-2222-2222-222222222222', 2, 'Ediciones Beta', 'Calle Falsa 456, Madrid'),
    ('33333333-3333-3333-3333-333333333333', 3, 'Gamma Books', '5th Avenue, New York');

-- ===========================
-- Authors
-- ===========================
INSERT INTO author (id, public_id, name, birthday, url_image)
VALUES
    ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 1, 'Gabriel García Márquez', '1927-03-06', 'https://img/garciamarquez.jpg'),
    ('aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 2, 'Isabel Allende', '1942-08-02', 'https://img/allende.jpg'),
    ('aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 3, 'Haruki Murakami', '1949-01-12', 'https://img/murakami.jpg'),
    ('aaaaaaa4-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 4, 'J. K. Rowling', '1965-07-31', 'https://img/rowling.jpg'),
    ('aaaaaaa5-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 5, 'Stephen King', '1947-09-21', 'https://img/king.jpg');

-- ===========================
-- Books (10 registros)
-- ===========================
INSERT INTO book (id, public_id, publisher_id, title, cover_url, edition, price, languages, epub_url, is_digital, is_deleted, created_by, created_date, modified_by, modified_date)
VALUES
    ('bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 1, '11111111-1111-1111-1111-111111111111', 'Cien Años de Soledad', 'https://img/soledad.jpg', '1ra', 19.99, 'ES', 'https://epub/soledad', true, false, 'admin', NOW(), 'admin', NOW()),
    ('bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 2, '11111111-1111-1111-1111-111111111111', 'El Amor en los Tiempos del Cólera', 'https://img/colera.jpg', '2da', 21.50, 'ES', 'https://epub/colera', true, false, 'admin', NOW(), 'admin', NOW()),
    ('bbbbbbb3-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 3, '22222222-2222-2222-2222-222222222222', 'La Casa de los Espíritus', 'https://img/espiritus.jpg', '1ra', 18.75, 'ES', 'https://epub/espiritus', false, false, 'admin', NOW(), 'admin', NOW()),
    ('bbbbbbb4-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 4, '22222222-2222-2222-2222-222222222222', 'Paula', 'https://img/paula.jpg', '3ra', 16.40, 'ES', 'https://epub/paula', true, false, 'admin', NOW(), 'admin', NOW()),
    ('bbbbbbb5-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 5, '33333333-3333-3333-3333-333333333333', 'Tokio Blues', 'https://img/tokio.jpg', '1ra', 22.00, 'ES,EN', 'https://epub/tokio', true, false, 'admin', NOW(), 'admin', NOW()),
    ('bbbbbbb6-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 6, '33333333-3333-3333-3333-333333333333', 'Kafka en la Orilla', 'https://img/kafka.jpg', '2da', 23.10, 'ES,EN', 'https://epub/kafka', true, false, 'admin', NOW(), 'admin', NOW()),
    ('bbbbbbb7-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 7, '11111111-1111-1111-1111-111111111111', 'Harry Potter y la Piedra Filosofal', 'https://img/hp1.jpg', '1ra', 25.99, 'ES,EN', 'https://epub/hp1', true, false, 'admin', NOW(), 'admin', NOW()),
    ('bbbbbbb8-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 8, '11111111-1111-1111-1111-111111111111', 'Harry Potter y la Cámara Secreta', 'https://img/hp2.jpg', '1ra', 26.50, 'ES,EN', 'https://epub/hp2', true, false, 'admin', NOW(), 'admin', NOW()),
    ('bbbbbbb9-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 9, '22222222-2222-2222-2222-222222222222', 'It', 'https://img/it.jpg', '1ra', 27.30, 'EN', 'https://epub/it', true, false, 'admin', NOW(), 'admin', NOW()),
    ('bbbbbb10-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 10, '33333333-3333-3333-3333-333333333333', 'El Resplandor', 'https://img/resplandor.jpg', '2da', 24.80, 'EN', 'https://epub/resplandor', true, false, 'admin', NOW(), 'admin', NOW());

-- ===========================
-- Author_Book (relaciones)
-- ===========================
INSERT INTO author_book (author_id, book_id)
VALUES
    ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbbb'),
    ('aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'bbbbbbb2-bbbb-bbbb-bbbb-bbbbbbbbbbbb'),
    ('aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'bbbbbbb3-bbbb-bbbb-bbbb-bbbbbbbbbbbb'),
    ('aaaaaaa2-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'bbbbbbb4-bbbb-bbbb-bbbb-bbbbbbbbbbbb'),
    ('aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'bbbbbbb5-bbbb-bbbb-bbbb-bbbbbbbbbbbb'),
    ('aaaaaaa3-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'bbbbbbb6-bbbb-bbbb-bbbb-bbbbbbbbbbbb'),
    ('aaaaaaa4-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'bbbbbbb7-bbbb-bbbb-bbbb-bbbbbbbbbbbb'),
    ('aaaaaaa4-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'bbbbbbb8-bbbb-bbbb-bbbb-bbbbbbbbbbbb'),
    ('aaaaaaa5-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'bbbbbbb9-bbbb-bbbb-bbbb-bbbbbbbbbbbb'),
    ('aaaaaaa5-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'bbbbbb10-bbbb-bbbb-bbbb-bbbbbbbbbbbb');
