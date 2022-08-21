create database bill_system;

create table users (
    id          serial primary key,
    username    text,
    email       text,
    passwd_md5  text,
    create_time timestamp not null default current_timestamp
);
create index idx_user_username on users (username);
insert into users(username, passwd_md5) values('admin', '21232f297a57a5a743894a0e4a801fc3');
select * from users;

create table session (
    id         serial primary key,
    user_id   integer,
    session_id text,
    token      text
);
create index idx_session_user_id on session (user_id);
create index idx_session_session_id on session (session_id);
select * from session;
select
       s.id,
       s.session_id,
       s.user_id,
       s.token
from session s join users u on u.id = s.user_id
where s.session_id = '29cc379d9c4e1c4dc5fab2cde190fb0ab9bb970f1b58f50f81d9e81d98d19ef6';

create table bill_item (
    id               serial primary key,
    name             text      not null,
    money            integer   not null,
    user_id          integer,
    bill_type_id     integer,
    bill_category_id integer,
    beneficiary_name text,
    merchant_id      text,
    pay_type_id      integer,
    pay_time         timestamp,
    create_time      timestamp not null default current_timestamp,
    update_time      timestamp not null default current_timestamp,
    memo             text
);
create index idx_bill_item_bill_type_id on bill_item (bill_type_id);
create index idx_bill_item_name on bill_item (name);
create index idx_bill_item_bill_category_id on bill_item (bill_category_id);
create index idx_bill_item_merchant_id on bill_item (merchant_id);
create index idx_bill_item_pay_type_id on bill_item (pay_type_id);

create table bill_type (
    id   serial primary key,
    name text
);
select * from bill_type;

create table bill_category (
    id        serial primary key,
    user_id   integer,
    name      text,
    parent_id integer
);
create index idx_bill_category_user_id on bill_category(user_id);

create table merchant (
    id        serial primary key,
    user_id   integer,
    name      text,
    logo_url  text
);
create index idx_merchant_user_id on merchant(user_id);

create table card (
    id               serial primary key,
    user_id          integer,
    card_category_id integer,
    balance          integer,
    card_sno         text,
    publisher        text
);
create index idx_card_card_category_id on card (card_category_id);
create index idx_card_user_id on card (user_id);

create table card_category (
    id        serial primary key,
    user_id   integer,
    name      text,
    parent_id integer
);
create index idx_card_category_user_id on card_category (user_id);
select *
from card_category;
