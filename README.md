# Music Management System

## Project overview

This application allows music stores to manage their stock system. Stock information among different local stores can be orgnized by Artist and Album.
Setup information is also provided to DevOps, you can easily setup your own music management system with least effort!

Technical key points: Java, SpringBoot, Hibernate, Maven, Flyway, PostgreSQL, Tomcat, AWS SQS, AWS S3

## Configure local environment

### Database server setup
First of all, you should pull PostgreSQL image shown bellow in docker:
```
docker pull postgres

docker run -name [container_name] -e POSTGRES_DB=[server_name] -e POSTGRES_USER=[admin] -e POSTGRES_PASSWORD=[password] -p 5432:5432 -d postgres
```
### Flyway migration

In this project, Flyway is used as the DB migration tool. Easily setup the environment using the folling commands:
```
mvn clean compile flyway:migrate -Ddatabase.driver=org.postgresql.Driver -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.url=${DB_URL} -Ddatabase.name=${DB_NAME} -Ddatabase.user=${DB_USER} -Ddatabase.password=${DB_PASSWORD} 
    
mvn test -Ddatabase.driver=org.postgresql.Driver -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.url=${DB_URL} -Ddatabase.name=${DB_NAME} -Ddatabase.user=${DB_USER} -Ddatabase.password=${DB_PASSWORD}
```

If you want to customize your database schema, you can store SQL files under the directory:
```
src/main/resources/db/migration
```
and all SQL files should abey the naming rule as:
```
V1.1__do_something_good.sql
V1.2__do_something_realy_good.sql
V2.0__do_something_awesome.sql
```
The version number should be unique and you should pay attention that there is two **underscore** between the version number and the file name.

### Build WAR file
After pulling this project and doing all tasks above, you should build the WAR file, which will later be deployed. 
```
mvn compile package -P dev -DskipTests=true
```

## API documentation

Here, I provide the pre-defined APIs that can be directly used.

### AuthController

Request a token
```
HTTP Method = GET
URL = http://host/auth/token
Query Params:
  Key = email, Value = example@abc.com
  Key = password, Value = 123456
Request Body Template:
{
  "name": "awesomeGuy",
  "password": "123456",
  "email": "example@abc.com"
}
```

### ArtistController

Get all artists without other information
```
HTTP Method = GET
URL = http://host/artists
```
Get all artists with other information
```
HTTP Method = GET
URL = http://host/artists/with-children
```
Get artist by artist name
```
HTTP Method = GET
URL = http://host/artists/${artist name}
```
Add new artist
```
HTTP Method = POST
URL = http://host/artists
Request Body Template:
{
  "name": "HotLearn",
  "startYear": 1999,
  "endYear": 0,
  "album": {
    ...
  }
}
```
Update an artist
```
HTTP Method = PUT
URL = http://host/artists
Request Body Template:
{
  "name": "HotLearn",
  "startYear": 1999,
  "endYear": 0,
  "album": {
    ...
  }
}
```
Delete an artist
```
HTTP Method = DELETE
URL = http://host/artists/${artist name}
```

### AlbumController
Get all albums without other information
```
HTTP Method = GET
URL = http://host/albums
```
Get all albums with other information
```
HTTP Method = GET
URL = http://host/albums/with-children
```
Get an album with the album name
```
HTTP Method = GET
URL = http://host/albums/${album name}
```
Create a new album
```
HTTP Method = POST
URL = http://host/albums/${artist name}
Request Body Template:
{
  "name": "Everyday Life",
  "releaseYear": 2020,
  "artist": {
    "name": "Hot Learn",
    "startYear": 1999,
    "endYear": 0,
    "description": "Best ever!"
  },
  "genre": "Rock",
  "description": "No description",
  "stock": {
    ...
  }
}
```
Update an album
```
HTTP Method = PUT
URL = http://host/albums
Request Body Template:
{
  "name": "Everyday Life",
  "releaseYear": 2020,
  "artist": {
    "name": "Hot Learn",
    "startYear": 1999,
    "endYear": 0,
    "description": "Best ever!"
  },
  "genre": "Rock",
  "description": "No description",
  "stock": {
    ...
  }
}
```
Delete an album
```
HTTP Method = DELETE
URL = http://host/albums/${album name}
```

### StockController
Get all stock information
```
HTTP Method = GET
URL = http://host/stocks
```
Get a specific album's stock infromation
```
HTTP Method = GET
URL = http://host/stocks/${album name}
```
Update a specific album's stock information
```
HTTP Method = PATCH
URL = http://host/stocks/${album name}/${value 1}/${value 2}/${value 3}/${value 4}/${value 5}
```

### FileController
Upload a file
```
HTTP Method = POST
URL = http://host/files/${AWS S3 bucket name}
Request Body form-data:
  Key = file, Value = ${the files you selected}
```
Download a file
```
HTTP Method = GET
URL = http://host/files/${file name}
```

## JVM Options for DevOps
```
-Ddatabase.driver=org.postgresql.Driver
-Ddatabase.url=${DB_URL}
-Ddatabase.name=${DB_NAME}
-Ddatabase.user=${DB_USER}
-Ddatabase.password=${DB_PASSWORD}
-Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect
-Dorg.slf4j.simpleLogger.defaultLogLevel=debug
```

