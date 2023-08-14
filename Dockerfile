FROM openjdk:17-jdk-slim-buster
EXPOSE 8081
ADD target/springBoot-0.0.1-SNAPSHOT.jar prodapp.jar
ENTRYPOINT ["java","-jar","/prodapp.jar"]