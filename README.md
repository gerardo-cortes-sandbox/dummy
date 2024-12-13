# dummy

## Commands

Test
```shell
mvn clean test
```

Package
```shell
mvn clean package
```

Run
```shell
java -jar target/dummy-0.0.1-SNAPSHOT.jar
```

Containerize
```shell
# mvn spring-boot:build-image
docker build --tag dummy:beta .
```

Run container
```shell
docker run --name dummy-container -p 8080:8080 dummy:beta
```

[Open Swagger](http://localhost:8080/swagger-ui/index.html)
