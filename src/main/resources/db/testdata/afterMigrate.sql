set foreign_key_checks=0;

delete from city;
delete from kitchen;
delete from state;
delete from payment_method;
delete from group_group;
delete from group_permission;
delete from permission;
delete from product;
delete from restaurant;
delete from restaurant_payment_method;
delete from user;
delete from user_group;

set foreign_key_checks=1;

alter table city auto_increment=1;
alter table kitchen auto_increment=1;
alter table state auto_increment=1;
alter table payment_method auto_increment=1;
alter table group_group auto_increment=1;
alter table permission auto_increment=1;
alter table product auto_increment=1;
alter table restaurant auto_increment=1;
alter table user auto_increment=1;

insert into kitchen (id, name) values (1, 'Thai');
insert into kitchen (id, name) values (2, 'Indian');
insert into kitchen (id, name) values (3, 'Italian');
insert into kitchen (id, name) values (4, 'French');

insert into state (id, name) values (1, 'Minas Gerais');
insert into state (id, name) values (2, 'São Paulo');
insert into state (id, name) values (3, 'Ceará');

insert into city (id, name, state_id) values (1, 'Uberlândia', 1);
insert into city (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into city (id, name, state_id) values (3, 'São Paulo', 2);
insert into city (id, name, state_id) values (4, 'Campinas', 2);
insert into city (id, name, state_id) values (5, 'Fortaleza', 3);

insert into restaurant (id, name, delivery_fee, kitchen_id, register_date, update_date, active, open, address_city_id, address_zipcode, address_street, address_number, address_district) values (1, 'Thai Gourmet', 10, 1, current_timestamp, current_timestamp, true, true, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurant (id, name, delivery_fee, kitchen_id, register_date, update_date, active, open) values (2, 'Nam Thai', 8.90, 1, current_timestamp, current_timestamp, true, true);
insert into restaurant (id, name, delivery_fee, kitchen_id, register_date, update_date, active, open) values (3, 'Amélie', 6.00, 4, current_timestamp, current_timestamp, true, true);
insert into restaurant (id, name, delivery_fee, kitchen_id, register_date, update_date, active, open) values (4, 'Cafeína', 7.49, 2, current_timestamp, current_timestamp, true, true);
insert into restaurant (id, name, delivery_fee, kitchen_id, register_date, update_date, active, open) values (5, 'La Trattoria', 11.99, 3, current_timestamp, current_timestamp, true, true);
insert into restaurant (id, name, delivery_fee, kitchen_id, register_date, update_date, active, open) values (6, 'Java Steakhouse', 11.99, 3.00, current_timestamp, current_timestamp, true, true);


insert into payment_method (id, description) values (1, 'Credit');
insert into payment_method (id, description) values (2, 'Debit');
insert into payment_method (id, description) values (3, 'Cash');

insert into permission (id, name, description) values (1, 'SEARCH KITCHENS', 'Allow search kitchens');
insert into permission (id, name, description) values (2, 'UPDATE KITCHENS', 'Allow update kitchens');

insert into restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4,1), (4,2), (5,1), (5,2), (5,3), (6,1), (6,3);

insert into product (name, description, price, active, restaurant_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into product (name, description, price, active, restaurant_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);

insert into product (name, description, price, active, restaurant_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);

insert into product (name, description, price, active, restaurant_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into product (name, description, price, active, restaurant_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);

insert into product (name, description, price, active, restaurant_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into product (name, description, price, active, restaurant_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);

insert into product (name, description, price, active, restaurant_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);

insert into product (name, description, price, active, restaurant_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 5);

insert into `group_group` (name) values ('Manager'), ('Salesman'), ('Secretary'), ('Register');

insert into user (id, name, email, password, register_date) values
(1, 'João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp),
(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp),
(3, 'José Souza', 'jose.aux@algafood.com', '123', utc_timestamp),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp);
