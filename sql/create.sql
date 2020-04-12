create sequence hibernate_sequence start 1 increment 1
create table transaction (id int8 not null, amount float8 not null, date timestamp, detail1 varchar(255), detail2 varchar(255), state varchar(255), category_id int8, parent_id int8, primary key (id))
create table transaction_category (id int8 not null, name varchar(255), primary key (id))
alter table if exists transaction add constraint FKormeo9tlr0vpkeh555nd0umgm foreign key (category_id) references transaction_category
alter table if exists transaction add constraint FK7xnki988uwmiu37cflu756xgb foreign key (parent_id) references transaction
