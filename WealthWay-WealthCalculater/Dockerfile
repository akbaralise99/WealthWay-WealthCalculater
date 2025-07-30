# Stage 1: Build
FROM maven:3.8.3-openjdk-17 AS build

COPY . .
RUN mvn clean install

# Stage 2: Run
FROM eclipse-temurin:17-jdk

COPY --from=build /target/WealthWay-0.0.1-SNAPSHOT.jar deom.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "deom.jar"]
