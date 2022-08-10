FROM openjdk:11
MAINTAINER milosz.artur
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8075
ENTRYPOINT ["java","-jar","/app.jar"]