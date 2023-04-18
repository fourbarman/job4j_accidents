create table if not exists accidents(
    id serial primary key,
    name varchar,
    text varchar,
    address varchar,
    type_id int references types(id)
);