FROM openjdk:17-oracle
MAINTAINER Matty

ADD ./target/web.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/web.jar"]

EXPOSE 8080

