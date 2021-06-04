Это веб-приложение на языке Java, которое я разрабатывал для закрепления знаний и получения новых, то есть по сути это мой учебный проект.
## Суть веб-приложения 
Это приложение автошколы, в котором есть три роли пользователей: клиент, инструктор и администратор.

**Клиент может:**
- просматривать список занятий, записаться на них, отменять запись;
- просматривать и очищать уведомления;
- просматривать свои оценки;
- просматривать и скачивать сертификаты в формате PDF.

**Инструктор может:**
- просматривать список занятий и записавшихся на них клиентов, отменять занятия (при отмете записавшимся клиентам отсылается уведомление)
- создавать новые занятия;
- выставлять оценки клиентами;
- просматривать список клиентов;

**Администратор может:**
- просматривать список клиентов, открывать и просматривать инфомрацию о любом клиенте, изменять лимит часов клиента, прикрепленного инструктора, выдавать сертификаты;
- просматривать список инструкторов, открывать и просматривать информацию о любом инструкторе, прикреплять к инструктору другую машину;
- регистрировать новые аккаунты для клиента и инструктора (по задумке только админ может регистраировать, т.к. данные берутся из договора);
- добавлять новые места занятий и новые машины.

Вход в приложение осуществляется в форме логина, перенаправление на нужный функционал происходит автоматически по логину+паролю, попытка зайти на недоступный
функционал вызовет ошибку.

## Технологии
В основном проект писался для закрепления и получения знания по Java и связанных с ней фреймворков, также чтобы подтянуть базовые знания фронтенда.
### Фронтенд 
- HTML;
- CSS;
- JavaScript;
- jQuery;
- Bootsrap;

### Бэкенд
- Spring Boot;
- Spring Security;

### БД
- Spring Data JPA (+Hinernate)
- СУБД MariaDB;

### Доп. информация
- система сборки Maven;
- Lombok для сокращения кода.

## Полезные ссылки
**Файл базы данных** - https://drive.google.com/file/d/1flONbBWDsVpCOVhVlnur1Wa3a2wzeblk/view?usp=sharing
**Видео работы приложения** - https://drive.google.com/file/d/1DKqOBIjv12NXGNBnof73ScL_8JWeAyO1/view?usp=sharing



## ----------------------------------Translated with Google Translator------------------------------

This is a Java web application that I developed to consolidate knowledge and gain new ones, that is, in fact, this is my training project.
## The essence of the web application
This is a driving school application that has three user roles: customer, instructor and administrator.

**Client can:**
- view the list of activities, sign up for them, cancel the entry;
- view and clear notifications;
- view your grades;
- view and download certificates in PDF format.

**The instructor can:**
- view the list of classes and the clients who signed up for them, cancel classes (when checked, a notification is sent to the signed up clients)
- create new activities;
- to give ratings by clients;
- view the list of clients;

**Administrator can:**
- view the list of clients, open and view information about any client, change the limit of hours of the client attached to the instructor, issue certificates;
- view the list of instructors, open and view information about any instructor, attach another car to the instructor;
- register new accounts for the client and the instructor (as planned, only the admin can register, since the data is taken from the contract);
- add new locations and new cars.

Login to the application is carried out in the form of a login, redirection to the desired functionality occurs automatically by login + password, an attempt to enter an unavailable
the functional will cause an error.

## Technologies
Basically, the project was written to consolidate and gain knowledge of Java and related frameworks, as well as to tighten up the basic knowledge of the frontend.
### Front end
- HTML;
- CSS;
- JavaScript;
- jQuery;
- Bootsrap;

### Backend
- Spring Boot;
- Spring Security;

### DB
- Spring Data JPA (+ Hinernate)
- MariaDB DBMS;

### Add. information
- Maven build system;
- Lombok for code shortening.

## Useful links
** Database file ** - https://drive.google.com/file/d/1flONbBWDsVpCOVhVlnur1Wa3a2wzeblk/view?usp=sharing
** Application video ** - https://drive.google.com/file/d/1DKqOBIjv12NXGNBnof73ScL_8JWeAyO1/view?usp=sharing 

