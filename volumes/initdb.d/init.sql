-- CREATE USER kintai;
-- CREATE DATABASE kintai;
-- GRANT ALL PRIVILEGES ON DATABASE kintai TO kintai;

CREATE TABLE IF NOT EXISTS member (
  PRIMARY KEY (id),
  id         VARCHAR(255) NOT NULL,
  email      VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL,
  created_at TIMESTAMP    NOT NULL,
  updated_at TIMESTAMP    NOT NULL,
             UNIQUE (email)
);
