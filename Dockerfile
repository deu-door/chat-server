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

COPY --from=build-stage build/libs/*.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "/app.jar"]