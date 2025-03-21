set foreign_key_checks=0;

lock tables city write, kitchen write, state write, payment_method write, group_group write, group_permission write,
     permission write, product write, restaurant write, restaurant_payment_method write, user write, user_group write,
     restaurant_user_responsible write, restaurant_order write, order_item write, product_photo write,
     oauth2_registered_client write;

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
delete from oauth2_registered_client;

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

insert into payment_method (id, description, date_updated) values
(1, 'Credit', utc_timestamp),
(2, 'Debit', utc_timestamp),
(3, 'Cash', utc_timestamp);

insert into permission (id, name, description) values
(1, 'EDIT_KITCHENS', 'It allows to edit kitchens'),
(2, 'EDIT_PAYMENT_METHODS', 'It allows to create or edit payment methods'),
(3, 'EDIT_CITIES', 'It allows to create or edit cities'),
(4, 'EDIT_STATES', 'It allows to create or edit states'),
(5, 'CONSULT_USERS_GROUPS_PERMISSIONS', 'It allows to consult users, groups, and permissions'),
(6, 'EDIT_USERS_GROUPS_PERMISSIONS', 'It allows to create or edit users, groups, and permissions'),
(7, 'EDIT_RESTAURANTS', 'It allows to create, edit or manage restaurants'),
(8, 'CONSULT_ORDERS', 'It allows to consult orders'),
(9, 'MANAGE_ORDERS', 'It allows to manage orders'),
(10, 'GENERATE_REPORTS', 'It allows to generate reports');


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
(2,'Seller'),
(3,'Assistant'),
(4,'Register');

-- Add all as permissons in manager group
insert into group_permission (group_id, permission_id)
select 1, id from permission;

-- Add permissions in seller group
insert into group_permission (group_id, permission_id)
select 2, id from permission where name like 'CONSULT_%';

insert into group_permission (group_id, permission_id) values (2, 7);

-- Add permissions in assistant group
insert into group_permission (group_id, permission_id)
select 3, id from permission where name like 'CONSULT_%';

-- Add permissions in register group
insert into group_permission (group_id, permission_id)
select 4, id from permission where name like '%_RESTAURANTS' or name like '%_PRODUCTS';

insert into user (id, name, email, password, register_date) values
    (1, 'John', 'john.man@algafood.com', '$2a$12$O4s/ZFIVXbXpMwT7Qg7Ir.CGs0puEOCfdb.T8Hdja6lrglCggzp9e', utc_timestamp),
(2, 'Mary', 'mary.sal@algafood.com', '$2a$12$O4s/ZFIVXbXpMwT7Qg7Ir.CGs0puEOCfdb.T8Hdja6lrglCggzp9e', utc_timestamp),
(3, 'Joseph', 'joseph.aux@algafood.com',    '$2a$12$O4s/ZFIVXbXpMwT7Qg7Ir.CGs0puEOCfdb.T8Hdja6lrglCggzp9e', utc_timestamp),
(4, 'Sebastian', 'sebastian.reg@algafood.com', '$2a$12$O4s/ZFIVXbXpMwT7Qg7Ir.CGs0puEOCfdb.T8Hdja6lrglCggzp9e', utc_timestamp),
(5, 'Matthew', 'matt.store@gmail.com'   , '$2a$12$O4s/ZFIVXbXpMwT7Qg7Ir.CGs0puEOCfdb.T8Hdja6lrglCggzp9e', utc_timestamp),
(6, 'Philippo Fiorentin', 'pipomytest@gmail.com', '$2a$12$O4s/ZFIVXbXpMwT7Qg7Ir.CGs0puEOCfdb.T8Hdja6lrglCggzp9e', utc_timestamp);

insert into user_group (user_id, group_id) values
(1, 1),
(1, 2),
(2, 2),
(3, 3),
(4, 4);

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

INSERT INTO oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('1', 'algafood-backend', '2022-11-29 18:58:12', '$2a$10$trk401po.Wx9JXXMs2xCFeB.eXU7qENFquETcr04a0hDJxGV3ge0.', NULL, 'AlgaFood Backend', 'client_secret_basic', 'client_credentials', '', 'READ', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",1800.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');

INSERT INTO oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('2', 'algafood-web', '2022-11-29 18:58:12', '$2a$10$/Lx1cVKanXiCkpYtdA369OZ78x8aHwx51RTxC.4pqEiuZRzQh0e/i', NULL, 'AlgaFood Web', 'client_secret_basic', 'refresh_token,authorization_code', 'http://127.0.0.1:8080/swagger-ui/oauth2-redirect.html,http://127.0.0.1:8080/authorized', 'READ,WRITE', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":false,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",900.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",86400.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');

INSERT INTO oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES('3', 'foodanalytics', '2022-11-29 18:58:12', '$2a$10$LQOU54Ta7zV7TxTXSk7DEeZUx/P9PwKGH5CTIOLNGWgIP29QHdq4K', NULL, 'Food Analytics', 'client_secret_basic', 'authorization_code', 'http://www.foodanalytics.local:8082', 'READ,WRITE', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",1800.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');


unlock tables;