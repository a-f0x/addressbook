CREATE TABLE phones
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    phone         VARCHAR(11) NOT NULL,
    phone_type_id INT         NOT NULL,
    contact_id    INT         NOT NULL,
    FOREIGN KEY (phone_type_id) REFERENCES phone_types (id)
)