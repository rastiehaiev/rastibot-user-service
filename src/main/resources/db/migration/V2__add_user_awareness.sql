ALTER TABLE user_table
    ADD COLUMN awareness INT DEFAULT NULL;

CREATE INDEX awareness_idx ON user_table (awareness);
