FROM eclipse-temurin:17
ARG JAR_FILE=target/*.jar
COPY ./target/proxyapi-java-v2.3.2.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]