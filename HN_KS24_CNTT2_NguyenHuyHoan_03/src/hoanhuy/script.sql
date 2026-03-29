
-- DROP TABLE IF EXISTS order_items;
-- DROP TABLE IF EXISTS orders;
-- DROP TABLE IF EXISTS reviews;
-- DROP TABLE IF EXISTS menu_items;
-- DROP TABLE IF EXISTS tables;
-- DROP TABLE IF EXISTS accounts;

create table accounts(
    id int auto_increment primary key,
    fullName varchar(255) not null,
    username varchar(255) unique not null,
    password varchar(255) not null,
    role enum('CUSTOMER' , 'MANAGER' , 'CHEF') not null,
    isBan boolean not null
);

create table tables(
    id int auto_increment primary key,
    isEmpty boolean not null,
    limited int not null,
    check(limited > 0)
);

create table menu_items(
    id int auto_increment primary key,
    name varchar(255) not null,
    price decimal(10,2),
    type enum('FOOD' , 'DRINK'),
    stock int default 0
);

create table orders(
    id int auto_increment primary key,
    customer_id int not null,
    table_id int not null,
    isPay boolean not null,
    created_at timestamp default current_timestamp,
    foreign key(customer_id) references accounts(id),
    foreign key(table_id) references tables(id)
);

create table order_items(
    id int auto_increment primary key,
    order_id int ,
    menu_item_id int ,
    quantity int ,
    status enum('PENDING_APPROVAL', 'REJECTED' , 'PENDING', 'COOKING', 'READY', 'SERVED'),
    foreign key(order_id) references orders(id),
    foreign key(menu_item_id) references menu_items(id)
);

create table reviews(
    id int auto_increment primary key,
    customer_id int,
    stars int default 0,
    comment varchar(255)
);
