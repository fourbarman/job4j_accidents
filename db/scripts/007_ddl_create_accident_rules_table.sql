create table if not exists accidents_rules(
    id serial primary key,
    accident_id int references accidents(id),
    rules_id int references rules(id)
)