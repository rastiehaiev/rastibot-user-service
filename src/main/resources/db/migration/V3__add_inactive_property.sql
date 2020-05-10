ALTER TABLE user_table
    ADD COLUMN inactive BOOLEAN DEFAULT FALSE;

CREATE INDEX inactive_idx ON user_table (inactive);