
FROM maven:3.6.0-jdk-11-slim AS build

COPY ./pom.xml ./pom.xml

RUN mvn -B -f pom.xml dependency:go-offline

COPY . .

RUN mvn -f ./pom.xml clean package


FROM openjdk:11
COPY --from=build target/*.jar /usr/local/lib/k8s-example.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/k8s-example.jar"]