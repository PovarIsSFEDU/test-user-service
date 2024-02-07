FROM eclipse-temurin:11-jdk-jammy as builder
ADD . /src
WORKDIR /src

RUN rm src/main/resources/application.properties && chmod +x mvnw


RUN --mount=type=cache,target=/root/.m2,rw ./mvnw clean package -B -am -DskipTests -T 1C



FROM eclipse-temurin:11-jdk-alpine
LABEL authors="p0v4r"

COPY --from=builder /src/target/test*.jar app.jar