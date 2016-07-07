/*
*  Create database
*/

drop database if exists inquirer;

create database inquirer; 


/*
*  Create tables
*/

use inquirer;

create table user (
id mediumint not null auto_increment,
name varchar(50),
age int,
primary key (id)
);

create table question (
id mediumint  not null auto_increment,
title varchar(50),
primary key (id)
);

create table answer (
id mediumint not null auto_increment,
title varchar (50),
question int unsigned not null references question(id),
isRight bool,
primary key (id)
);

/*
*  Insert values
*/

insert into user(name,age) values ('admin', 20);
insert into user(name,age) values ('test user', 25);

insert into question(title) values ('Right answer is 3');

insert into answer(title, question, isRight) values ( '1', 1, false);
insert into answer(title, question, isRight) values ( '2', 1, false);
insert into answer(title, question, isRight) values ( '3', 1, true);
insert into answer(title, question, isRight) values ( '4', 1, false);




commit;