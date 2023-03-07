# job4j_accidents
Repository for "accidents" project:

Start developing app for register car accidents.


This project is based on [JDK 17](https://www.oracle.com/java/technologies/javase-downloads.html#JDK17) and will use:
- [Maven](https://maven.apache.org/) (v. 3.6.3),
- [Springboot](https://spring.io/) (v. 2.7.3),
- [Bootstrap](https://getbootstrap.com/docs/4.4/getting-started/introduction/) (v. 4.4.1)
- [Thymeleaf](https://www.thymeleaf.org/)


Tests use [JUnit5](https://junit.org/junit5/) and [Mockito](https://site.mockito.org/).

## articles
1. 2 roles - Users and Traffic Inspectors.
2. User can add accidents with address, car id number, accident description and photo.
3. Accident will have status: accepted, rejected, closed.
4. Main window: search bar with result table.
## build and start
Steps to start a program from sources:
1. Go through terminal\cmd to project sources and use maven command to generate .jar:
```mvn package```
2. If the project compiles successfully You will see folder "target" with generated _job4j_accidents-1.0-SNAPSHOT.jar_.
3. Execute it from terminal\cmd with command:
```java -jar job4j_accidents-1.0-SNAPSHOT.jar```

### Contacts
Feel free for contacting me:
- **Skype**: pankovmv
