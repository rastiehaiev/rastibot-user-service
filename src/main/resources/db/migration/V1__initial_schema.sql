CREATE TABLE user_table
(
    id         SERIAL PRIMARY KEY,
    chat_id    INT NOT NULL UNIQUE,
    username   VARCHAR(255) DEFAULT NULL,
    first_name VARCHAR(255) DEFAULT NULL,
    last_name  VARCHAR(255) DEFAULT NULL,
    locale     VARCHAR(255) DEFAULT NULL
);

CREATE INDEX chat_id_idx ON user_table (chat_id);

ALTER TABLE user_table OWNER TO "rastibot-user-service";