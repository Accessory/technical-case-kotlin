FROM gradle:latest as builder
#FROM eclipse-temurin:17-jdk-alpine as builder
WORKDIR /home/run/
#COPY gradle ./gradle
COPY src ./src
#COPY gradlew gradlew
#COPY .gradle ./.gradle
COPY build.gradle.kts build.gradle.kts
COPY settings.gradle.kts settings.gradle.kts
RUN gradle bootJar --no-daemon --no-watch-fs

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /home/run/
COPY --from=builder /home/run/build/libs/*.jar /home/run/app.jar
CMD ["java", "-jar", "/home/run/app.jar"]

