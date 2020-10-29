FROM maven:3.6-jdk-11 AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package

FROM fabric8/java-alpine-openjdk11-jre
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/challenge-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "challenge-0.0.1-SNAPSHOT.jar"]
