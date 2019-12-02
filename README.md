# Music Management System

## Project overview

In this project, I utilize docker to set up the database server, use flyway to build database schema, and use JDBC (Hibernate in the future) to communicate between Java IDE and PostgreSQL database. In this project, PGAdmin 4 is also used to easily check out the status and schema of the database.

## Database server setup

Use 'docker' to setup local environment, i.e., create the postgreSQL server container and run it.

	docker pull postgres
	docker run 
	-name [container_name] 
	-e POSTGRES_DB=[server_name] 
	-e POSTGRES_USER=[admin] 
	-e POSTGRES_PASSWORD=[password]
	-p 5432:5432 
	-d postgres

## Flyway usage

In this project, Flyway is used as the DB migration tool. All SQL queries are stored under the directory

	src/main/resources/db/migration
	
and all SQL files should abey the naming rule as:

	V1.1__do_something_good.sql
	V1.2__do_something_realy_good.sql
	V2.0__do_something_awesome.sql
	
The version number should be unique and you should pay attention that there is two **underscore** between the version number and the file name.

After the setup, you can do SQL queries in your project root directory using this command:

	mvn flyway: migrate
	
