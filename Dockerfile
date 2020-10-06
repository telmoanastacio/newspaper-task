FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ADD target target
ADD target/*.jar target/app.jar
ENTRYPOINT ["sh", "-c", "java -jar /target/app.jar"]