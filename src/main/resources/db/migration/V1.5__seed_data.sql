INSERT INTO contacts(first_name, last_name, address)
VALUES ('Eugeniy', 'Doronin', 'Kemerovo city, Lenina st. 4');


INSERT INTO phones (number, phone_type_id, contact_id)
VALUES ('+79236150001', (SELECT id FROM phone_types WHERE type = 'MOBILE'),
        (SELECT id FROM contacts WHERE first_name = 'Eugeniy'));

INSERT INTO phones (number, phone_type_id, contact_id)
VALUES ('+79236110011', (SELECT id FROM phone_types WHERE type = 'WORK'),
        (SELECT id FROM contacts WHERE first_name = 'Eugeniy'));