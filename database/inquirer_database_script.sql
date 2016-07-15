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
title text,
primary key (id)
);

create table answer (
id mediumint not null auto_increment,
title text,
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

insert into question(title) values ('Что такое Servlet?');

insert into answer(title, question, isRight) values ( 'Библиотека', 1, false);
insert into answer(title, question, isRight) values ( 'Класс', 1, false);
insert into answer(title, question, isRight) values ( 'Интерфейс', 1, true);
insert into answer(title, question, isRight) values ( 'Салат из овощей', 1, false);

insert into question(title) values ('Для чего нужен web.xml файл?');

insert into answer(title, question, isRight) values ( 'Определяет перечень используемых url', 2, false);
insert into answer(title, question, isRight) values ( 'Определяет соответствие сервлет и отображаемых ими jsp файлов', 2, false);
insert into answer(title, question, isRight) values ( 'Определяет, какие URL будут передаваться определенному сервлету', 2, true);

insert into question(title) values ('Что есть jsp файл?');

insert into answer(title, question, isRight) values ( 'Сервлет', 3, true);
insert into answer(title, question, isRight) values ( 'Подтип html документа', 3, false);

insert into question(title) values ('В чем различие между redirect и forward?');

insert into answer(title, question, isRight) values ( 'Redirect возвращает на предыдущую страницу, Forward на ранее не посещенную', 4, false);
insert into answer(title, question, isRight) values ( 'Redirect возвращает на текущую страницу в случае ошибки, Forward отправляет по указанной ссылке', 4, false);
insert into answer(title, question, isRight) values ( 'Redirect посылает ответ браузеру, который затем делает запрос на новый url, Forward пересылает запрос на целевой url на сервере', 4, true);

insert into question(title) values ('В чем отличие методов GET и POST?');

insert into answer(title, question, isRight) values ( 'GET передает данные серверу используя URL, когда POST передает данные, используя тело HTTP запроса.', 5, true);
insert into answer(title, question, isRight) values ( 'GET забирает данные со страницы, POST отправляет их на страницу', 5, false);

insert into result(user,mark) values (1,10);
insert into result(user,mark) values (1,8);


commit;