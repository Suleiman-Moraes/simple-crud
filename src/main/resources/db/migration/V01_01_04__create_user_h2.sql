CREATE TABLE users (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    username VARCHAR(255) NOT NULL,
    `password` VARCHAR(255),
    account_non_expired BOOLEAN,
    account_non_locked BOOLEAN,
    credentials_non_expired BOOLEAN,
    `enabled` BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE
    IF EXISTS users
ADD
    CONSTRAINT unique_user_username UNIQUE (username);