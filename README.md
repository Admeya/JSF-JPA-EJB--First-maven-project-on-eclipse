# JSF-JPA-EJB--First-maven-project-on-eclipse
Первая работающая сборка JSF

Сервер WildFly
Консоль администрирования http://localhost:9990/console
Там задаем JNDI - источник БД MySQL

MySQL - database Storage;

create table users (usr_id int not null auto_increment, usr_name char(30)
 not null, usr_login char(30) not null, usr_pass char(30) not null, primary key
(usr_id));
insert into users (usr_name, usr_login, usr_pass) values ('Admin', 'user'
, '123');
