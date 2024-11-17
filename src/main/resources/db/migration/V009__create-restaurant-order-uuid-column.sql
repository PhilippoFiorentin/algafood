alter table restaurant_order add uuid varchar(36) not null after id;
update restaurant_order set uuid = uuid();
alter table restaurant_order add constraint uk_restaurant_order_uuid unique (uuid);