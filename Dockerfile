FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/roastapp-0.0.1-SNAPSHOT.jar app.jar
ENV PORT=8080
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT} -jar app.jar"]