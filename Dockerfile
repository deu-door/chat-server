# -----------
# Build Stage
# -----------

FROM openjdk:14-alpine AS build-stage

COPY gradlew .

COPY gradle gradle

COPY build.gradle .

COPY settings.gradle .

COPY src src

RUN chmod +x ./gradlew

RUN ./gradlew bootJar

# ----------------
# Production Stage
# ----------------

FROM openjdk:14-alpine

RUN apk update && apk add bash

WORKDIR /

COPY --from=build-stage build/libs/*.jar app.jar

COPY wait-for-it.sh .

RUN chmod +x ./wait-for-it.sh

EXPOSE 8000

# shell form
ENTRYPOINT ./wait-for-it.sh $MYSQL_HOST -- java -jar /app.jar