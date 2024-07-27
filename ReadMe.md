# Reference:
[https://nitinkc.github.io/spring/microservices/springboot-reference/](https://nitinkc.github.io/spring/microservices/springboot-reference/)

# Prerequisites:

**Run Postgres DB** 

- URL : jdbc:postgresql://localhost:5432/mydb
- user name : dbuser
- password : a
- DB Name : mydb
- schema : test
- app yml property : spring.datasource.url=jdbc:postgresql://localhost:5432/mydb?currentSchema=test


**Run Redis on docker**

Run Docker Desktop on Local

```shell
docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest
```
Access the Redis UI

[http://localhost:8001/redis-stack/browser](http://localhost:8001/redis-stack/browser)

# Run Prometheus and Grafana

```shell
docker run -p 9090:9090 -v /Users/nichaurasia/Programming/SpringBootProjects/SpringBoot-reference/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus

docker run -d --name grafana -p 3000:3000 grafana/grafana
```

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

Instead of native queries, Java Persistence Query Language (JPQL) can be used
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
            .id(studentJpaRepository.getNextSequence())//Introducing Sequence, fetch Sequence from Repository Interface
            .firstName(studentSave.firstName())
            ........
            .build();

    Student savedStudent = studentJpaRepository.save(student);
    return savedStudent.getId();//Default method of JpaRepository
}
```

# Dockerizing the Spring Boot application

create a file `Dockerfile`

```dockerfile
# Pickup the right image
FROM eclipse-temurin:17-jdk-alpine
MAINTAINER <email_id>

# Set up work directory
WORKDIR /app
# Copy the jar file into the work
COPY build/libs/*.jar /app
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

# Upload a pic
Upload a pic to test via postman form-data uploaded as a file

```shell
curl --location 'localhost:8089/api/upload' \
--form 'file=@"/Users/nichaurasia/Programming/SpringBootProjects/SpringBoot-GlobalExceptionHandling/src/main/resources/pic.png"'
```
![form-data.png](src%2Fmain%2Fresources%2Fform-data.png)

# Documentation
[http://localhost:8090/swagger-ui/index.html#/](http://localhost:8090/swagger-ui/index.html#/)
```properties
springdoc.swagger-ui.enabled = true
springdoc.swagger-ui.path = /swagger-ui.html
springdoc.swagger-ui.tryItOutEnabled = true
springdoc.swagger-ui.filter = false
springdoc.swagger-ui.syntaxHighlight.activated = true
```

### Metrics
[http://localhost:8090/actuator/metrics/system.cpu.count](http://localhost:8090/actuator/metrics/system.cpu.count)

[http://localhost:8090/actuator/metrics/process.cpu.usage](http://localhost:8090/actuator/metrics/process.cpu.usage)

[http://localhost:8090/actuator/metrics/jvm.gc.pause](http://localhost:8090/actuator/metrics/jvm.gc.pause)


### Prometheus

[http://localhost:8090/actuator/prometheus](http://localhost:8090/actuator/prometheus)

**metrics**

[http://localhost:8090/actuator/metrics](http://localhost:8090/actuator/metrics)
```properties
## Actuator Settings
#management.endpoints.web.exposure.include=health,info, metrics
management.endpoint.health.show-details=always
management.endpoint.health.enabled=true
#Enable Actuator endpoints
management.endpoints.web.exposure.include=*
management.context-path=/actuator
management.health.vault.enabled=false
management.health.cassandra.enabled=false
management.health.mail.enabled=false
management.endpoint.env.show-values=ALWAYS
management.endpoint.configprops.show-values=ALWAYS
management.prometheus.metrics.export.enabled=true
```