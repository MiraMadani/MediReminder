# Етап 1: Збірка проєкту через Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Етап 2: Запуск готового додатку
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/medireminder-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]