FROM openjdk:8-jdk-alpine
MAINTAINER LXC <lf98zcxc@gmail.com>
COPY ./weather-info.jar /app.jar
WORKDIR /
EXPOSE 9001
CMD ["java", "-jar", "/app.jar"]