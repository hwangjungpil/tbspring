create table if not exists users (
    id varchar(255) NOT NULL,
    name varchar(255) not null,
    password varchar(255) not null,
primary key(id)
);