CREATE TABLE IF NOT EXISTS authorities
(
    id SERIAL PRIMARY KEY,
    authority VARCHAR(50) NOT NULL UNIQUE
);