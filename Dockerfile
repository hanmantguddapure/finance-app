FROM openjdk:8-jdk-alpine
MAINTAINER baeldung.com
COPY target/finance-services.jar finance-services.jar
ENTRYPOINT ["java","-jar","/finance-services.jar"]