# job4j_accidents
Repository for "accidents" project:

Start developing app for register car accidents.


This project is based on [JDK 17](https://www.oracle.com/java/technologies/javase-downloads.html#JDK17) and use:
- [Maven](https://maven.apache.org/) (v. 3.6.3),
- [Springboot](https://spring.io/projects/spring-boot) (v. 2.7.3),
- [Spring security](https://spring.io/projects/spring-security) (v. 5.7.3),
- [Spring security web](https://spring.io/projects/spring-security) (v. 5.7.3),
- [Spring security config](https://spring.io/projects/spring-security) (v. 5.7.3),
- [Spring security test](https://spring.io/projects/spring-security) (v. 5.7.3),
- [Spring Data](https://spring.io/projects/spring-data) (v. 5.7.3),
- [Lombok](https://projectlombok.org/) (v. 1.18.22),
- [H2 database](https://www.h2database.com/html/main.html) (v. 1.18.22),
- [PostgreSQL](https://www.postgresql.org/) (v. 42.2.12),
- [Hibernate](https://hibernate.org/) (v. 5.6.11.Final),
- [Bootstrap](https://getbootstrap.com/docs/4.4/getting-started/introduction/) (v. 4.4.1)
- [Thymeleaf](https://www.thymeleaf.org/)

Tests use [JUnit5](https://junit.org/junit5/) and [Mockito](https://site.mockito.org/).

## articles
1. Project uses Spring security authentication and authorization (use default username and password or create Your own).
- ![auth_form](https://github.com/fourbarman/screenshots/blob/main/job4j_accidents/auth_form.png)
2. Error when trying to log in with wrong credentials.
- ![auth_form_error](https://github.com/fourbarman/screenshots/blob/main/job4j_accidents/auth_form_error.png)
3. Register form.
- ![register_form](https://github.com/fourbarman/screenshots/blob/main/job4j_accidents/register_form.png)
4. Register form shows error when trying to register user with already existing username.
- ![register_form_error](https://github.com/fourbarman/screenshots/blob/main/job4j_accidents/register_form_error.png)
5. Main window: list of accidents, link to Create new accident, logout.
- ![main_view](https://github.com/fourbarman/screenshots/blob/main/job4j_accidents/main_view.png)
6. You can ether edit accident with link on main view...
- ![accident_update](https://github.com/fourbarman/screenshots/blob/main/job4j_accidents/accident_update.png)
7. ...or create a new one.
- ![accident_new](https://github.com/fourbarman/screenshots/blob/main/job4j_accidents/accident_new.png)

## build and start
Steps to start a program from sources:
1. Go through terminal\cmd to project sources and use maven command to generate .jar:
```mvn package```
2. If the project compiles successfully You will see folder "target" with generated _job4j_accidents-1.0-SNAPSHOT.jar_.
3. Execute it from terminal\cmd with command:
```java -jar job4j_accidents-1.0-SNAPSHOT.jar```
4. Open browser and go to ```http://localhost:8080/index```
5. Use default username and password:
- ```user secret```
- ```admin secret```
- or register new one.
### TODO
1. Add accident status (i.e. "new", "in progress", "accepted")
2. Add accident photo.
3. Add proper authorization.

### Contacts
Feel free for contacting me:
- **Skype**: pankovmv
