CREATE TABLE contacts
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NULL,
    address    VARCHAR(512) NULL
)