create table if not exists ingredients (
id varchar(4) not null primary key,
name varchar(25) not null,
type varchar(25) not null
);

create table if not exists tacos (
id identity not null primary key,
name varchar(50) not null,
created_at timestamp not null
);

create table if not exists tacos_ingredients (
taco_id bigint not null,
ingredient_id varchar(4) not null
);

alter table tacos_ingredients add foreign key (taco_id) references tacos(id);
alter table tacos_ingredients add foreign key (ingredient_id) references ingredients(id);

drop table if exists orders cascade;

create table if not exists orders (
id identity not null primary key,
name varchar(50) not null,
street varchar(50) not null,
city varchar(50) not null,
state varchar(50) not null,
zip varchar(50) not null,
cc_number varchar(16) not null,
cc_expiration varchar(5) not null,
cc_cvv varchar(3) not null,
placed_at timestamp not null
);

create table if not exists orders_tacos (
order_id bigint not null,
taco_id bigint not null
);

alter table orders_tacos add foreign key (order_id) references orders(id);
alter table orders_tacos add foreign key (taco_id) references tacos(id);
