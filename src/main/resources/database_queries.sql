
create database hospital_management;

use hospital_management;

create table staff( id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY, email_id varchar(255) NOT NULL,password varchar(255) NOT NULL, enabled tinyint(1) NOT NULL );

create table patient (
id bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
name varchar(255) NOT NULL ,
age int NOT NULL,
room_no varchar(127) NOT NULL,
doctor_name varchar(127) NOT NULL,
expense int NOT NULL,
status varchar(127) NOT NULL,
admit_date DATETIME NOT NULL,
created_by varchar(127) NOT NULL,
modified_by varchar(127)  NOT NULL );


