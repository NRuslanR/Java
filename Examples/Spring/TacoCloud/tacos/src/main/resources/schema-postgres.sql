drop database if exists tacos;
drop user if exists taco_user;

create user taco_user with encrypted password '123456' createdb;

set role taco_user;

create database tacos;

\c tacos

create table users (
    id bigint not null primary key generated always as identity,
    username varchar(200) unique not null ,
    password varchar (500) not null,
    fullname varchar(500) not null,
    street varchar(500) not null,
    city varchar(500) not null,
    state varchar(500) not null,
    zip varchar(500) not null,
    phone_number varchar(500) not null
);

alter table users add constraint unique_username unique (username);

create table users_authorities(
    username varchar(200) not null,
    authority varchar(200) not null
);

alter table users_authorities add foreign key (username) references users(username);
alter table users_authorities add primary key (username, authority);

create table ingredients (
id varchar(4) not null primary key,
name varchar(25) not null,
type varchar(25) not null
);

create table tacos (
id bigint generated always as identity not null primary key,
name varchar(50) not null,
created_at timestamp not null
);

create table tacos_ingredients (
taco_id bigint not null,
ingredient_id varchar(4) not null
);

alter table tacos_ingredients add foreign key (taco_id) references tacos(id);
alter table tacos_ingredients add foreign key (ingredient_id) references ingredients(id);

create table orders (
id bigint generated always as identity not null primary key,
name varchar(50) not null,
street varchar(50) not null,
city varchar(50) not null,
state varchar(50) not null,
zip varchar(50) not null,
cc_number varchar(16) not null,
cc_expiration varchar(5) not null,
cc_cvv varchar(3) not null,
placed_at timestamp not null,
customer_id bigint not null
);

alter table orders add constraint orders_user_id__users_id_fkey foreign key (customer_id) references users(id);

create table orders_tacos (
order_id bigint not null,
taco_id bigint not null
);

alter table orders_tacos add foreign key (order_id) references orders(id);
alter table orders_tacos add foreign key (taco_id) references tacos(id);

insert into ingredients (id, name, type)
values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into ingredients (id, name, type)
values ('COTO', 'Corn Tortilla', 'WRAP');
insert into ingredients (id, name, type)
values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into ingredients (id, name, type)
values ('CARN', 'Carnitas', 'PROTEIN');
insert into ingredients (id, name, type)
values ('TMTO', 'Diced Tomatoes Own', 'VEGGIES');
insert into ingredients (id, name, type)
values ('LETC', 'Lettuce', 'VEGGIES');
insert into ingredients (id, name, type)
values ('CHED', 'Cheddar', 'CHEESE');
insert into ingredients (id, name, type)
values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into ingredients (id, name, type)
values ('SLSA', 'Salsa', 'SAUCE');
insert into ingredients (id, name, type)
values ('SRCR', 'Sour Cream', 'SAUCE');

grant connect on database tacos to taco_user;
grant all privileges on all tables in schema public to taco_user;
grant usage on all sequences in schema public to taco_user;
grant execute on all functions in schema public to taco_user;


