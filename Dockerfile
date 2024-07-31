FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/user-e-commerce.jar user-e-commerce.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "user-e-commerce.jar"]