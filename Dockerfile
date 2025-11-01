FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/*.jar tuan-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "tuan-0.0.1-SNAPSHOT.jar"]
