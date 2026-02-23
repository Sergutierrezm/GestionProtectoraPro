# Stage 1: Build con Maven y JDK 17
FROM maven:3.9.3-eclipse-temurin-17 AS build

WORKDIR /app

# Copia pom.xml y src desde el contexto (ya estamos en backend/)
COPY pom.xml .
COPY src ./src

# Compila el proyecto y genera el JAR
RUN mvn clean package -DskipTests

# Stage 2: Contenedor final con JDK 17
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copia el JAR generado en el stage de build
COPY --from=build /app/target/GestionProtectoraPro-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]