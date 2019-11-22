FROM openjdk:8-jdk-alpine
ADD target/spring-boot-h2-docker.jar spring-boot-h2-docker.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-boot-h2-docker.jar"]