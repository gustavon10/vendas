# Etapa 1: Construir o projeto usando Maven
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests

# Etapa 2: Executar o projeto com o JAR gerado
FROM openjdk:26-ea-17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/vendas-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-Dspring.h2.console.settings.web-allow-others=true", "-jar", "/app/app.jar"]
#ENTRYPOINT ["java", "-jar", "/app/app.jar"]