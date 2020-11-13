FROM openjdk:14-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "/app.jar"]