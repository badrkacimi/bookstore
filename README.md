# BADR KACIMI - Blue Harvest - KATA

# Technical stack :

* Java 21 Spring boot 3.3.4.RELEASE.
* Docker for containerize the application.
* Mapstruct for java bean mappings at compile-time.
* Lombok to minimize the boilerplate code
* H2 as in-memory database.
* Basic authentication using Spring Security's form login.

# Business requirement :

User registration:

```
/auth/register
```

# Build project

To build the project, run the maven command

```
mvn clean install
```

# Run the project

```
docker build -t bookstore:latest .
docker run -p 8080:8080 bookstore:latest 
```

# Visualize & interact with the API's endpoints :

URL : http://localhost:8080/swagger-ui/index.html
