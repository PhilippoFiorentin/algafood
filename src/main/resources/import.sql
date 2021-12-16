insert into kitchen (id, name) values (1, 'Tailandesa');
insert into kitchen (id, name) values (2, 'Indiana');
insert into kitchen (id, name) values (3, 'Italiana');
insert into kitchen (id, name) values (4, 'Francesa');

insert into restaurant (name, delivery_fee, kitchen_id)values ('Nam Thai', 8.90, 1);
insert into restaurant (name, delivery_fee, kitchen_id) values ('Amélie', 6.00, 4);
insert into restaurant (name, delivery_fee, kitchen_id) values ('Cafeína', 7.49, 2);
insert into restaurant (name, delivery_fee, kitchen_id) values ('La Trattoria', 11.99, 3);
insert into restaurant (name, delivery_fee, kitchen_id) values ('La Mole', 10.45, 3);

insert into state (id, name) values (1, 'Minas Gerais');
insert into state (id, name) values (2, 'São Paulo');
insert into state (id, name) values (3, 'Ceará');

insert into city (id, name, state_id) values (1, 'Uberlândia', 1);
insert into city (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into city (id, name, state_id) values (3, 'São Paulo', 2);
insert into city (id, name, state_id) values (4, 'Campinas', 2);
insert into city (id, name, state_id) values (5, 'Fortaleza', 3);

insert into payment_method (id, description) values (1, 'Credit');
insert into payment_method (id, description) values (2, 'Debit');
insert into payment_method (id, description) values (3, 'Cash');

insert into permission (id, name, description) values (1, 'SEARCH KITCHENS', 'Allow search kitchens');
insert into permission (id, name, description) values (2, 'UPDATE KITCHENS', 'Allow update kitchens');

insert into restaurant_payment_method (restaurant_id, payment_method_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4,1), (4,2), (5,1), (5,2), (5,3);
