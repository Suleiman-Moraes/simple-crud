CREATE TABLE IF NOT EXISTS person (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    address VARCHAR(255),
    first_name VARCHAR(255) NOT NULL,
    gender VARCHAR(255),
    last_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);