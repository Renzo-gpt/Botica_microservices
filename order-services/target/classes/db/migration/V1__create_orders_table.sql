CREATE TABLE IF NOT EXISTS orders (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id     BIGINT NOT NULL,
    date            DATETIME NOT NULL,
    total           DECIMAL(10, 2) NOT NULL,
    payment_type    VARCHAR(50) NOT NULL
);