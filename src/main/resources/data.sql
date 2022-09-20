INSERT INTO PERSON (id, name, email, password_hash, is_admin, workspace)
VALUES ('1fd2ab72-ed5d-412d-b567-ebd6b643b89d', 'Fabio Geisser', 'fabio.geisser@gmail.com', '12345', true, NULL ),
       ('bc90f453-1225-472c-8269-6875f7fe953a', 'Lucia Albanese', 'lucia.albanese@gmail.com', '434332', false, 'Kunstlerin');

INSERT INTO BOOKING (id, checkin, checkout, amount, approved, fk_person)
VALUES ('0243c612-edc9-497c-aa34-e1856bfd6d14', '2022-10-10', '2022-10-20', 4, false, 'bc90f453-1225-472c-8269-6875f7fe953a'),
       ('e1cc2997-e693-4420-bc8e-e0c599a39a04', '2022-10-15', '2022-10-25', 6, true, '1fd2ab72-ed5d-412d-b567-ebd6b643b89d');