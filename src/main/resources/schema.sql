DROP TABLE IF EXISTS PERSON CASCADE;
CREATE TABLE PERSON
(
    id            UUID,
    name          VARCHAR(2000) NOT NULL,
    email         VARCHAR(2000) NOT NULL,
    password_hash VARCHAR(2000) NOT NULL,
    is_admin      BOOLEAN       NOT NULL DEFAULT FALSE,
    workspace     VARCHAT(2000)

    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS BOOKING CASCADE;
CREATE TABLE BOOKING
(
    id       UUID,
    checkin  DATE,
    checkout DATE,
    amount   int,
    approved BOOL,
    fk_person UUID,

    PRIMARY KEY (id),
    FOREIGN KEY (fk_person) REFERENCES PERSON (id)
);