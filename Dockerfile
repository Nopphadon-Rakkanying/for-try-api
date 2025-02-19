FROM openjdk:21-jdk

COPY target/for-try-api-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 9090

CMD ["java", "-jar", "/app/app.jar"]