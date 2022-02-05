delete from orders_tacos;
delete from tacos_ingredients;
delete from tacos;
delete from users;
delete from orders;
delete from ingredients;
delete from users_authorities;

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

insert into users (username, password, fullname, street, city, state, zip, phone_number) 
values ('ruslan', '123456', 'Nigmatullin Ruslan', 'erger', 'sdf', 'fsdf', '5454', '343');

insert into users (username, password, fullname, street, city, state, zip, phone_number) 
values ('valery', '000000', 'Yakovishin Valery', 'grtrt', 'rer', 'ger', '435', '34433');

insert into users_authorities (username, authority) 
values ('ruslan', 'ROLE_ADMIN'), ('valery', 'ROLE_USER');