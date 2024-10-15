create table restaurant_order (
    id bigint not null auto_increment,
    subtotal decimal(10,2) not null,
    delivery_fee decimal(10,2) not null,
    total decimal(10,2) not null,

    creation_date datetime not null,
    confirmation_date datetime null,
    cancellation_date datetime null,
    delivery_date datetime null,

    payment_method_id bigint not null,
    restaurant_id bigint not null,
    user_client_id bigint not null,
    status varchar(10) not null,

    address_city_id bigint(20) not null,
    address_zipcode varchar(9) not null,
    address_street varchar(100) not null,
    address_number varchar(20) not null,
    address_additional varchar(60) null,
    address_district varchar(60) not null,

    primary key(id),

    constraint fk_payment_method foreign key (payment_method_id) references payment_method(id),
    constraint fk_restaurant_order foreign key (restaurant_id) references restaurant(id),
    constraint fk_user_client foreign key (user_client_id) references user(id),
    constraint fk_address_city foreign key (address_city_id) references city(id)
)engine=InnoDB default charset=utf8;

create table order_item(
    id bigint not null auto_increment,
    quantity smallint(6) not null,
    unitary_price decimal(10,2) not null,
    total_price decimal (10,2) not null,
    observation varchar(255) null,
    order_id bigint not null,
    product_id bigint not null,

    primary key(id),
    unique key uk_product_order_item(order_id, product_id),

    constraint fk_order_item_restaurant_order foreign key(order_id) references restaurant_order(id),
    constraint fk_order_item_product foreign key(product_id) references product(id)
)engine=InnoDB default charset=utf8;
