# Music Management System

## Project overview

In this project, I utilize docker to set up the database server, use flyway to build database schema, and use JDBC and Hibernate to communicate between Java IDE and PostgreSQL database. In this project, PGAdmin 4 is also used to easily check out the status and schema of the database.

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

## From JDBC to Hibernate

JDBC (Java Database Connectivity) is the basic API provided by Java to achieve the functionalities of SQL like database connectivities. In this project, I use Hibernate framework, which uses JDBC as its low-level realization, however provides more powerful functions.

To use Hibernate, there are two things you need to do.

The first thing is to add dependency in POM file, it should be like this:

	<dependency>
      <groupId>org.hibernate</groupId>
      <artifactId>hibernate-core</artifactId>
      <version>5.4.3.Final</version>
    </dependency>
    <dependency>
      <groupId>com.github.v-ladynev</groupId>
      <artifactId>fluent-hibernate-core</artifactId>
      <version>0.3.1</version>
    </dependency>

The second thing here is to add annotations to your model classes, you can find these in Album.java, Artist.java, and Stock.java, a simple example is shown as follow:
	
	@Entity
	@Table(name = "album")
	public class Album {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String name;
    @Column(name = "release_year")
    private int releaseYear;
    @Column
    private String artist;
    @Column
    private String genre;
    @Column
    private String description;
    @Id
    @Column(name = "serial_num")
    private String serialNumber;
    ...
    Some getter() and setter()

Here, @Table maps your model class to a SQL table, @Column maps your class' property to a column in the table.
You can read the reference links here to get more information:
https://www.tutorialspoint.com/hibernate/index.htm
https://dzone.com/articles/all-hibernate-annotations-mapping-annotations


	
