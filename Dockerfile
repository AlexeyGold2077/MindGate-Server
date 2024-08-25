FROM openjdk:17
COPY target/MindGate-0.2.0.jar /app/
WORKDIR /app
EXPOSE 8080
CMD ["java", "-jar", "MindGate-0.1.6.jar"]