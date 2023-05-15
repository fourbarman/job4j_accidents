CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE ,
    password VARCHAR(128) NOT NULL,
    enabled  boolean default true,
    authority_id int not null references authorities(id)
);