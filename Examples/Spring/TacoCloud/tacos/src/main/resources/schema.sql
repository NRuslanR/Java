create table if not exists ingredients (
id varchar(4) not null,
name varchar(25) not null,
type varchar(10) not null
);

create table if not exists tacos (
id identity,
name varchar(50) not null,
createdAt timestamp not null
);

create table if not exists tacos_ingredients (
taco_id bigint not null,
ingredient_id varchar(4) not null
);

alter table tacos_ingredients add foreign key (taco_id) references tacos(id);
alter table tacos_ingredients add foreign key (ingredient_id) references ingredients(id);

drop table if exists orders cascade;

create table if not exists orders (
id identity,
name varchar(50) not null,
street varchar(50) not null,
city varchar(50) not null,
state varchar(50) not null,
zip varchar(50) not null,
ccNumber varchar(16) not null,
ccExpiration varchar(5) not null,
ccCVV varchar(3) not null,
placedAt timestamp not null
);

create table if not exists tacos_orders (
order_id bigint not null,
taco_id bigint not null
);

alter table tacos_orders add foreign key (order_id) references orders(id);
alter table tacos_orders add foreign key (taco_id) references tacos(id);

select * from ingredients;