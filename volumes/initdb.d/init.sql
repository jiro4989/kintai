-- CREATE USER kintai;
-- CREATE DATABASE kintai;
-- GRANT ALL PRIVILEGES ON DATABASE kintai TO kintai;

CREATE TABLE IF NOT EXISTS "user"
(id SERIAL, email TEXT, password TEXT,
 PRIMARY KEY (id),
 UNIQUE (email));
