FROM openjdk:11

LABEL "maintainer"="lukasz.styk@klikwplik.pl"

USER root

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN sed -i 's/\r$//' mvnw && ./mvnw dependency:resolve

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]
