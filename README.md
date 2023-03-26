# Weather Application
## Description
The application is designed to provide the latest weather data, average weather data for a certain period of time.

## Features
- CRUD operation for Weather
   (opportunity create, getAll)
- post for User
- get for getAll Users
- UUID as an ID parameter
- custom Exception
- UnitTest (Junit 5 + Mockito)
- Docker compose


## Tech
Receipt application uses next technologies:
- Java 11
- Spring Boot
- Hibernate
- Mysql 8
- Maven
- Open API
- RESTFUL
- JUnit 5
- Mockito
- Docker compose


## GUIDE

- run sql "user-service.sql" in folder "ddl" *.sql (1 bd -> 1 schema -> 1 table)
- RESTFUL info in folder "spec" *.yaml
- unitTests in folder "test"
## Run application on Spring Boot
### Normal application launch
First you need to import the sql file into Mysql.
The project can be run through the development environment, or you can make and use a jar file.
To check the application in Postman, you can use the ready-made collection Bootcamp.postman_collection.json
### Running with docker
Create project jar file via maven package. There may be problems with connecting to the database. Connecting to Mysql requires changing the host from localhost to db (the port remains the same).
Then we run docker-compose.yml. Images for Mysql, proxy, minio and java project will be created and containers will be raised.
Connection data is specified in docker-compose.yml.


