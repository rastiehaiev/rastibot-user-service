CREATE TABLE user_table
(
    id            SERIAL PRIMARY KEY,
    chat_id       INT NOT NULL UNIQUE,
    locale        VARCHAR(255) DEFAULT NULL
);