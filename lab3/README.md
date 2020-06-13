# Car park
It is my third lab by OOP course. 
Dispatcher distributes trip applications between drivers, each of which has its own car. On trip may be assigned 
a car in good condition and whose characteristics correspond to the application. The driver makes a mark about
trip performance and car condition.

## Table of contents
* [Clone](#Clone)
* [Setup](#Setup)
* [Configure](#Configure)
* [Technologies](#technologies)

## Clone
```
git clone https://github.com/MariKush/OOP2.git
```

## Setup 
### BackEnd
```
$ cd Server
$ mvn clean spring-boot:run
```
### FrontEnd
```
$ cd Client
$ ng serve
```

## Configure
in Server/src/main/resources/*.properties change username, password and url on your

## Technologies
Project is created with:
* Java: 8
* SpringBoot
* Spring Security
* Angular: 9
* PostgreSQL
* Hibernate
* Spring Data
* Liquidbase
* Keycloak
