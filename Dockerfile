FROM eclipse-temurin:21-jdk-alpine as build

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN chmod +x mvnw
RUN --mount=type=cache,target=/root/.m2 ./mvnw install -DskipTests

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} target/rinhabackend.jar
RUN java -Djarmode=layertools -jar target/rinhabackend.jar extract --destination target/extracted

FROM eclipse-temurin:21-jre-alpine
RUN addgroup -S padrao && adduser -S padrao -G padrao
VOLUME /tmp
USER padrao
ARG EXTRACTED=target/extracted
WORKDIR application
COPY --from=build ${EXTRACTED}/dependencies/ ./
COPY --from=build ${EXTRACTED}/spring-boot-loader/ ./
COPY --from=build ${EXTRACTED}/snapshot-dependencies/ ./
COPY --from=build ${EXTRACTED}/application/ ./
ENTRYPOINT ["java","org.springframework.boot.loader.launch.JarLauncher"]