FROM eclipse-temurin:17
ARG JAR_FILE=target/*.jar
COPY ./target/proxyapi-java-v1.1.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]