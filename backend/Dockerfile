# Etapa 1: construir la app
FROM maven:3.9.3-eclipse-temurin-17 AS build

WORKDIR /app

# Copia pom.xml y código fuente
COPY pom.xml .
COPY src ./src

# Compila el proyecto y genera el .jar
RUN mvn clean package -DskipTests

# Etapa 2: contenedor runtime
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copia el .jar desde la etapa de build
COPY --from=build /app/target/GestionProtectoraPro-1.0-SNAPSHOT.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Comando para arrancar la app
ENTRYPOINT ["java", "-jar", "app.jar"]