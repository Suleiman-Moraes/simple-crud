CREATE TABLE user_permission (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    id_permission BIGINT,
    id_user BIGINT,
    PRIMARY KEY (id)
);

ALTER TABLE
    IF EXISTS user_permission
ADD
    CONSTRAINT unique_user_permission_id_user_id_permission UNIQUE (id_user, id_permission);

ALTER TABLE
    IF EXISTS user_permission
ADD
    CONSTRAINT forein_user_permission_id_permission FOREIGN KEY (id_permission) REFERENCES permission;

ALTER TABLE
    IF EXISTS user_permission
ADD
    CONSTRAINT forein_user_permission_id_user FOREIGN KEY (id_user) REFERENCES users;