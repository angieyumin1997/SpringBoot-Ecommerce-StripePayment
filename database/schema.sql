drop schema if exists ecommerce;

create schema ecommerce;

use ecommerce;

create table users(
    username varchar(128) not null,
    password varchar(256) not null,
    role enum('ROLE_USER','ROLE_ADMIN') default 'ROLE_USER',
    customer_name varchar(128) not null,
    customer_address varchar(128) not null,
    customer_number varchar(128) not null,

    primary key(username)
);

create table category(
    category_id int auto_increment not null,
    category_name varchar(128) not null,
    description varchar(128) not null,

    primary key(category_id)
);

create table products(
    prod_id int auto_increment not null,
    image mediumblob not null,
    name varchar(128) not null,
    description varchar(128) not null,
    price double not null,

    category_id int not null,

    primary key(prod_id),

    constraint fk_category_id
        foreign key(category_id)
        references category(category_id)
);

create table orders(
    order_id int auto_increment not null,
    total_amount double not null,
    order_date date not null,
    payment_intent varchar(128),
    payment_intent_client_secret varchar(128),

    username varchar(128) not null,

    primary key(order_id),

    constraint fk_username
        foreign key(username)
        references users(username)
);

create table cart(
    cart_id int auto_increment not null,
    payment_status enum('p','np') default 'np' not null,
    price double not null,
    quantity int not null,
    size enum('xs','s','m','l','xl') not null,

    prod_id int not null,
    username varchar(128) not null,
    order_id int,
    prod_name varchar(128) not null,

    primary key(cart_id),

    constraint fk_cart_prod_id
        foreign key(prod_id)
        references products(prod_id),
    constraint fk_cart_username
        foreign key(username)
        references users(username),
    constraint fk_cart_order_id
        foreign key(order_id)
        references orders(order_id)

);