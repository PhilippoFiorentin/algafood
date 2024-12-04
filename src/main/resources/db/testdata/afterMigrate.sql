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
delete from restaurant_user_responsible;
delete from restaurant_order;
delete from order_item;
delete from product_photo;

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
alter table restaurant_order auto_increment = 1;
alter table order_item auto_increment = 1;

insert into kitchen (id, name) values (1, 'Thai');
insert into kitchen (id, name) values (2, 'Indian');
insert into kitchen (id, name) values (3, 'Italian');
insert into kitchen (id, name) values (4, 'French');

insert into state (id, name) values
(1, 'Minas Gerais'),
(2, 'São Paulo'),
(3, 'Ceará');

insert into city (id, name, state_id) values
(1, 'Uberlândia', 1),
(2, 'Belo Horizonte', 1),
(3, 'São Paulo', 2),
(4, 'Campinas', 2),
(5, 'Fortaleza', 3);

insert into restaurant (id, name, delivery_fee, kitchen_id, register_date, update_date, active, open, address_city_id, address_zipcode, address_street, address_number, address_district) values
(1, 'Thai Gourmet', 10, 1, current_timestamp, current_timestamp, true, true, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');

insert into restaurant (id, name, delivery_fee, kitchen_id, register_date, update_date, active, open) values
(2, 'Nam Thai', 8.90, 1, current_timestamp, current_timestamp, true, true),
(3, 'Amélie', 6.00, 4, current_timestamp, current_timestamp, true, true),
(4, 'Cafeína', 7.49, 2, current_timestamp, current_timestamp, true, true),
(5, 'La Trattoria', 11.99, 3, current_timestamp, current_timestamp, true, true),
(6, 'Java Steakhouse', 11.99, 3.00, current_timestamp, current_timestamp, true, true);

insert into payment_method (id, description) values
(1, 'Credit'),
(2, 'Debit'),
(3, 'Cash');

insert into permission (id, name, description) values
(1, 'SEARCH KITCHENS', 'Allow to search kitchens'),
(2, 'UPDATE KITCHENS', 'Allow update kitchens');

insert into restaurant_payment_method (restaurant_id, payment_method_id) values
(1, 1),
(1, 2),
(1, 3),
(2, 3),
(3, 2),
(3, 3),
(4,1),
(4,2),
(5,1),
(5,2),
(5,3),
(6,1),
(6,3);

insert into product (name, description, price, active, restaurant_id) values
('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 0, 1),
('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1),
('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2),
('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3),
('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3),
('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4),
('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4),
('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5),
('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 5);

insert into `group_group` (id, name) values
(1,'Manager'),
(2,'Salesman'),
(3,'Secretary'),
(4,'Register');

insert into group_permission (group_id, permission_id) values
(1, 1),
(1, 2),
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(4, 1),
(4, 2);

insert into user (id, name, email, password, register_date) values
(1, 'João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp),
(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp),
(3, 'José Souza', 'jose.aux@algafood.com', '123', utc_timestamp),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp),
(5, 'Manoel Lima', 'manoel.loja@gmail.com', '123', utc_timestamp),
(6, 'Philippo Fiorentin', 'test@gmail.com', '123', utc_timestamp);

insert into user_group (user_id, group_id) values
(1, 1),
(1, 2),
(2, 2);

insert into restaurant_user_responsible (restaurant_id, user_id) values
(1, 5),
(3, 5);

insert into restaurant_order(
id,
uuid,
subtotal,
delivery_fee,
total,
creation_date,
payment_method_id,
restaurant_id,
user_client_id,
status,
address_city_id,
address_zipcode,
address_street,
address_number,
address_additional,
address_district) values(
1,
'65ce4ac1-95bb-40e2-b1fa-0cdb693ff0e3',
298.90,
10,
308.90,
utc_timestamp,
1,
1,
6,
'CREATED',
1,
'38400-000',
'Rua Floriano Peixoto',
'500',
'Apto 801',
'Centro');

insert into order_item (id, quantity, unitary_price, total_price, observation, product_id, order_id) values
(1, 1, 78.9, 78.9, null, 1,1),
(2, 2, 110, 220, 'less spicy, please', 2, 1);

insert into restaurant_order(
id,
uuid,
subtotal,
delivery_fee,
total,
creation_date,
payment_method_id,
restaurant_id,
user_client_id,
status,
address_city_id,
address_zipcode,
address_street,
address_number,
address_additional,
address_district) values(
2,
'067baaee-5c89-4617-9e23-fd648761d67a',
79,
0,
79,
utc_timestamp,
2,
4,
6,
'CREATED',
1,
'38400-111',
'Rua Acre',
'300',
'Casa 2',
'Centro');

insert into order_item (id, quantity, unitary_price, total_price, observation, product_id, order_id) values
(3, 1, 79, 79, 'Medium', 6, 2);

insert into restaurant_order(
id,
uuid,
subtotal,
delivery_fee,
total,
creation_date,
confirmation_date,
delivery_date,
payment_method_id,
restaurant_id,
user_client_id,
status,
address_city_id,
address_zipcode,
address_street,
address_number,
address_additional,
address_district) values(
3,
'b5741512-8fbc-47fa-9ac1-b530354fc0ff',
110,
10,
120,
'2019-10-30 21:10:00',
'2019-10-30 21:10:45',
'2019-10-30 21:55:44',
1,
1,
6,
'DELIVERED',
1,
'38400-222',
'Rua Natal',
200 ,
null,
'Brasil');

insert into order_item (id, quantity, unitary_price, total_price, observation, product_id, order_id) values
(4, 1, 110, 110, null, 2, 3);

insert into restaurant_order(
id,
uuid,
subtotal,
delivery_fee,
total,
creation_date,
confirmation_date,
delivery_date,
payment_method_id,
restaurant_id,
user_client_id,
status,
address_city_id,
address_zipcode,
address_street,
address_number,
address_additional,
address_district) values(
4,
'5c621c9a-ba61-4454-8631-8aabefe58dc2',
174.4,
5,
179.4,
'2019-11-02 20:34:04',
'2019-11-02 20:35:10',
'2019-11-02 21:10:32',
1,
1,
6,
'DELIVERED',
1,
'38400-800',
'Rua Fortaleza',
'900',
'Apto 504',
'Centro');

insert into order_item (id, quantity, unitary_price, total_price, observation, product_id, order_id) values
(5, 2, 87.2, 174.4, null, 3, 4);

insert into restaurant_order(
id,
uuid,
subtotal,
delivery_fee,
total,
creation_date,
confirmation_date,
delivery_date,
payment_method_id,
restaurant_id,
user_client_id,
status,
address_city_id,
address_zipcode,
address_street,
address_number,
address_additional,
address_district) values(
5,
'8d774bcf-b238-42f3-aef1-5fb388754d63',
87.2,
10,
97.2,
'2019-11-03 02:00:30',
'2019-11-03 02:01:21',
'2019-11-03 02:20:10',
2,
3,
6,
'DELIVERED',
1,
'38400-200',
'Rua 10',
'930',
'Casa 20',
'Martins');

insert into order_item (id, quantity, unitary_price, total_price, observation, product_id, order_id) values
(6, 1, 87.2, 87.2, null, 3, 5);