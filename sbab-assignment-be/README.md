# SBAB assignment consuming trafiklab api

## Develop

### Running

#### Run local
``mvn spring-boot:run``

# sbab assignment 
An API hosting a couple of endpoints using data from Trafiklab’s open API (https://www.trafiklab.se).

The API offers the following features:
- Return the details of bus lines that have the most bus stops on their route.
- Return a list of names of every bus stop for each of the bus lines in the top 10 list.

## Key Technologies Used
- Java 17
- Spring Boot
    - embedded tomcat
    - H2 DB
    - scheduler
    - webflux (for integration tests)
- Junit 5
- Mockito
- slf4j
- Maven
- commons-lang3

### Endpoints
http://localhost:8080/api/toplines
Get TopLines api response
```
[
    {
        "id": 294,
        "lineNumber": "636",
        "defaultTransport": "BUS"
    },
    {     "id": 284,
        "lineNumber": "626",
        "defaultTransport": "BUS"
    }
 ]
```
http://localhost:8080/api/stopsbyname/636
Get StopNames api response
```
[
    {
        "id": 6864,
        "stopPoint": "60059",
        "stopName": "Norrtälje busstation"
    },
    {
        "id": 6866,
        "stopPoint": "60061",
        "stopName": "Älmsta busstation"
    }
 ]
```

## Documentation

## Spring help - Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/reference/htmlsingle/#web)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/reference/htmlsingle/#actuator)
* [Prometheus](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/reference/htmlsingle/#actuator.metrics.export.prometheus)

#### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

Merge conflice comment..