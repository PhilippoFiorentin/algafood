create table product_photo (
    product_id bigint not null,
    filename varchar(150) not null,
    description varchar(150),
    content_type varchar(80) not null,
    size int not null,

    primary key (product_id),
    constraint fk_product_photo_photo foreign key (product_id) references product(id)
) engine=InnoDB default charset=utf8;