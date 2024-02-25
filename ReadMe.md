
[https://nitinkc.github.io/spring/microservices/spring-revisions/](https://nitinkc.github.io/spring/microservices/spring-revisions/)

# Postgres DB Integration

The data source URL takes in the DB name and `?currentSchema` as the name of the schema

```yaml
spring.datasource.url=jdbc:postgresql://localhost:5432/mydb?currentSchema=test
spring.datasource.username= 
spring.datasource.password=

spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
spring.datasource.driver-class-name=org.postgresql.Driver
```

JpaRepository extendsCrudRepository and provides support for Pagination and Sorting.
### Custom query

With the Repository, custom queries can be added
```java
@Repository
@EnableJpaRepositories
@Qualifier("jpaStudentRepository")
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Custom query - ONLY for Simple Queries,
    @Query(nativeQuery = true, value = "SELECT * from student s WHERE s.gender = null")
    List<Student> findMaleStudents();

```
`nativeQuery = true` ensures that it runs with Selected DB
```java
@Query(nativeQuery = true, value = "SELECT * from student s WHERE s.gender = 'Male'")
List<Student> findMaleStudents();
```

Instead of native queries, JPQL can be used
```java
// Custom query using JPQL - Java Persistence Query Language
@Query("SELECT s FROM Student s WHERE s.gender = 'Female'")
List<Student> findMaleStudentsJpql();
```

JPQL with Query parameter
```java
// Custom query, JPQL, with Query Parameter
@Query("SELECT s FROM Student s WHERE s.dob > :date")
List<Student> findByDobDateAfter(@Param("date") LocalDate date);
```


# PUT Calls

# Postgres Db On Conflict Insert Statement

```sql

insert into student 
    (id, first_name, last_name, gender, cityofbirth, email, university, dob) 
values (1, 'Anjanette', 'Pietrzyk', 'Female', 'Ugljevik', 'apietrzyk0@wufoo.com', 'Sul Ross State University', '1972-01-30')
On CONFLICT(id) DO NOTHING;
```



# Introducing Sequence

Sequence can be created with `schema.sql` file and can be used while inserting in the DB

```sql
CREATE SEQUENCE IF NOT EXISTS student_seq
     AS INT
    INCREMENT BY 2 --Increment by 2
     MAXVALUE 10000
    START WITH 1005
     NO CYCLE
    OWNED BY student.id
```

Fetch the Postgres Sequence from repository

```java
@Query(nativeQuery = true, value = "SELECT nextval('student_seq');")
int getNextSequence();
```

From Service set the primary Key using the `getNextSequence()` method above

```java
public int saveStudent(StudentSave studentSave){
    Student student = Student.builder()
            //.id(4000)// If Primary key is hardcoded, it behaves like an update statement
            .id(studentRepository.getNextSequence())//Introducing Sequence, fetch Sequence from Repository Interface
            .firstName(studentSave.firstName())
            ........
            .build();

    Student savedStudent = studentRepository.save(student);
    return savedStudent.getId();//Default method of JpaRepository
}
```

# Dockerizing the Spring Boot application

create a file `Dockerfile`

```dockerfile
# Pickup the right image
FROM eclipse-temurin:17-jdk-alpine
MAINTAINER suchismitadeb2000@gmail.com

# Set up work directory
WORKDIR /app
# Copy the jar file into the work
COPY build/libs/SpringBoot-Basics-0.0.1-SNAPSHOT.jar /app
# Exposing port as per app yml/property
EXPOSE 8090
# Starting the application
CMD ["java", "-jar", "SpringBoot-Basics-0.0.1-SNAPSHOT.jar"]
```

The Springboot application depends on Postgres DB running on localhost. In case of docker, we can create postgres db 
as service inside the docker container and then run the spring boot application

# Docker Compose

01-init.sh

```dockerfile
 volumes: # Providing the customized path to 01-init.sh for entrypoint
      - ./src/main/resources/db:/docker-entrypoint-initdb.d/
```
   

The init file has to be given execute permission

```shell
cd db
chmod 777 01-init.sh
```

The final docker compose file 

```dockerfile
version: '3.8'

services:
  postgres:
    image: postgres:13.1
    healthcheck:
      test: [ "CMD", "pg_isready", "-q", "-d", "postgres", "-U", "root" ]
      timeout: 45s
      interval: 10s
      retries: 10
    restart: always
    environment:
      - POSTGRES_USER=root
      - POSTGRES_PASSWORD=password
      - APP_DB_USER=nichaurasia
      - APP_DB_PASS=a
      - APP_DB_NAME=mydb
      - APP_DB_SCHEMA=test
    volumes: # Providing the customized path to 01-init.sh for entrypoint
      - ./src/main/resources/db:/docker-entrypoint-initdb.d/
    ports:
      - 5432:5432

  app:
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8090:8090"
    depends_on:
      - postgres
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/mydb?currentSchema=test
      SPRING_DATASOURCE_USERNAME: nichaurasia
      SPRING_DATASOURCE_PASSWORD: a
```

Documentation

[JSON Report : http://localhost:8090/v3/api-docs](http://localhost:8090/v3/api-docs)

[HTML Report : http://localhost:8090/swagger-ui/index.html](http://localhost:8090/swagger-ui/index.html)