# Hospital Management System

This is a Hospital Management System implemented using Java Spring Boot. It provides a web application for hospital staff to manage patients, including user authentication and various patient-related operations via REST APIs.

## Table of Contents

1. [Features](#features)
2. [Requirements](#requirements)
3. [Setup and Usage](#setup-and-usage)
4. [API Endpoints](#api-endpoints)
5. [Database Schema](#database-schema)
6. [Code Structure](#code-structure)

## Features

- SignUp/Login for hospital staff.
- Admit new patients with details such as name, age, room, doctor name, admit date, expenses, and status (admitted, discharged).
- Fetch a list of all admitted patients.
- Discharge patients from the hospital.
- Port which the Application will be deployed in is 8080

## Requirements

To run this application, you need:

- Java Development Kit (JDK) 8 or higher
- Have used java17
- Gradle (for building)
- MySQL or another relational database (configured in application.properties) Add the username and password in place of spring.datasource.username and spring.datasource.password
- Please Execute the Sql commands and queries at your local before starting server,queries can be found in `src/main/resources/database_queries.sql`.
- Postman or a similar tool for testing the APIs Have written the API's below along with that have attached the postman collection in `postmanCollection/`

## Setup and Usage

1. Clone the repository to your local machine:
   git clone git@github.com:Yashkothari9/HospitalManagementSystem.git
2. Navigate to the project directory:
   cd hospitalManagementSystem
3.  Configure your database connection in `src/main/resources/application.properties`.
4. Build the project
5. Run the Project
6. Access the application at `http://localhost:8080`.
   


## API Endpoint: 
- ### Health Check:
  GET `/p/health`\
  CURL :- `curl --location 'http://localhost:8080/p/health'`
- ### SignUp Of Staff:
  POST `/api/hms/staff/signUp` \
  CURL :- `curl --location 'http://localhost:8080/api/hms/staff/signUp' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "emailId":"y@google.com",
  "password":"xyz@123"
  }'`
- ### Login Of Staff:
  POST `/api/hms/staff/login`\
  CURL :- `curl --location 'http://localhost:8080/api/hms/staff/login' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "emailId":"k@google.com",
  "password":"xyz@123"
  }'`
- ### Add Patient:
  POST `/api/hms/patient/add` \
  ADDITIONAL DATA:- Send the `staffEmailId` from header, who is LoggedIn \
  CURL :- `curl --location 'http://localhost:8080/api/hms/patient/add' \
  --header 'staffEmailId: k@google.com' \
  --header 'Content-Type: application/json' \
  --data '{
  "name":"Mady",
  "age":"21",
  "roomNo":"204",
  "doctorName":"Dr.Laxman",
  "admitDate":"2019-04-28T14:45:15",
  "expense":"20000",
  "status":"ADMITTED"
  }'`
- ### Get Patients Admitted to hospital:
  GET `/api/hms/patient/admitted` \
  ADDITIONAL DATA :- Send the `staffEmailId` from header, who is LoggedIn\
  CURL :-
  `curl --location 'http://localhost:8080/api/hms/patient/admitted' \
  --header 'staffEmailId: k@google.com'`
- ### Discharge Patient from Hospital: 
  POST `api/hms/patient/discharge/{patientId}` \
  ADDITIONAL DATA :- Send the `staffEmailId` from header, who is LoggedIn and Send data of `patientId` from path param who needs to be discharged\
  CURL :-
  `curl --location --request POST 'http://localhost:8080/api/hms/patient/discharge/{patientId}' \
  --header 'staffEmailId: k@google.com'`
- ### Update Patient: 
  GET `/api/hms/patient/updated` \
  ADDITIONAL DATA :- Send the `staffEmailId` from header, who is LoggedIn\
  CURL :-
  `curl --location 'http://localhost:8080/api/hms/patient/update' \
  --header 'staffEmailId: y@google.com' \
  --header 'Content-Type: application/json' \
  --data '{
  "name":"Yash",
  "age":"21",
  "roomNo":"201",
  "doctorName":"Dr.P",
  "expense":"2000",
  "status":"ADMITTED"
  }'`

## Database Schema
- ### Staff:
id(Primary Key) : Long\
emailId : String\
enabled : Boolean\
password : String

- ### Patient:
id(Primary Key) : Long\
name : String\
age : Integer\
roomNo : Integer\
doctorName : String\
admitDate : Date\
expense : Integer\
status : Status\
createdBy : String\
modifiedBy : String

- ### Mysql Commands:
For Mysql Commands for table creation you can refer in the following file :
`src/main/resources/database_queries.sql`. and can run in your local machine before running the code

## Code Structure

- `src/main/java/com/example/hospitalManagement/`: Java source code.
- `src/main/resources/`: application property file `application.properties`.
- `postmanCollection/`: Postman collection for API testing.

## Assumtions:
- I am assuming that this server will login one staff at a time 
and will allow only that staff to update, if some other staff want to
update he will have to call the login function.
- SignUp end point is to add the staff in staff database,but
he has to call the login API to log in and then update
- I am not implementing signOut, If one staff sign's in
then he will replace the other staff who was previously signed in
- I am assuming that by default the Mysql port is 3306,but if it something else then do update the `spring.datasource.url` in applicaition.properties

