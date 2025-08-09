FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY pom.xml .
RUN ./usr/local/openjdk-21/bin/jlink --version || true
RUN --mount=type=cache,target=/root/.m2 mvn -q -e -DskipTests dependency:go-offline || true
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -q -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
