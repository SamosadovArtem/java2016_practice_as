/*
* Create database
*/

drop database if exists inquirer;

create database inquirer;


/*
* Create tables
*/

use inquirer;

create table user (
id mediumint not null auto_increment,
name varchar(50),
age int,
primary key (id)
);

create table question (
id mediumint not null auto_increment,
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

create table result(
id mediumint not null auto_increment,
user int unsigned not null references user(id),
mark int not null,
primary key (id)
);

/*
* Insert values
*/

insert into user(name,age) values ('admin', 20);
insert into user(name,age) values ('test user', 25);

insert into question(title) values ('Question 1');

insert into answer(title, question, isRight) values ( '1', 1, false);
insert into answer(title, question, isRight) values ( '2', 1, false);
insert into answer(title, question, isRight) values ( '3', 1, true);
insert into answer(title, question, isRight) values ( '4', 1, false);

insert into question(title) values ('Question 2');

insert into answer(title, question, isRight) values ( '1', 2, false);
insert into answer(title, question, isRight) values ( '2', 2, true);
insert into answer(title, question, isRight) values ( '3', 2, false);

insert into question(title) values ('Question 3');

insert into answer(title, question, isRight) values ( '1', 3, false);
insert into answer(title, question, isRight) values ( '2', 3, true);
insert into answer(title, question, isRight) values ( '3', 3, false);

insert into question(title) values ('Question 4');

insert into answer(title, question, isRight) values ( '1', 4, true);
insert into answer(title, question, isRight) values ( '2', 4, false);

insert into question(title) values ('Question 5');

insert into answer(title, question, isRight) values ( '1', 5, false);
insert into answer(title, question, isRight) values ( '2', 5, false);
insert into answer(title, question, isRight) values ( '3', 5, true);

insert into question(title) values ('Question 6');

insert into answer(title, question, isRight) values ( '1', 6, false);
insert into answer(title, question, isRight) values ( '2', 6, true);
insert into answer(title, question, isRight) values ( '3', 6, false);
insert into answer(title, question, isRight) values ( '4', 6, false);

insert into question(title) values ('Question 7');

insert into answer(title, question, isRight) values ( '1', 7, false);
insert into answer(title, question, isRight) values ( '2', 7, true);
insert into answer(title, question, isRight) values ( '3', 7, false);

insert into question(title) values ('Question 8');

insert into answer(title, question, isRight) values ( '1', 8, true);
insert into answer(title, question, isRight) values ( '2', 8, false);

insert into question(title) values ('Question 9');

insert into answer(title, question, isRight) values ( '1', 9, false);
insert into answer(title, question, isRight) values ( '2', 9, false);
insert into answer(title, question, isRight) values ( '3', 9, false);
insert into answer(title, question, isRight) values ( '4', 9, true);

insert into question(title) values ('Question 10');

insert into answer(title, question, isRight) values ( '1', 10, false);
insert into answer(title, question, isRight) values ( '2', 10, true);
insert into answer(title, question, isRight) values ( '3', 10, false);

insert into result(user,mark) values (1,10);
insert into result(user,mark) values (1,8);


commit;