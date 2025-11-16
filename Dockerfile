FROM eclipse-temurin:21-jdk-jammy

# Set the working directory inside the container
WORKDIR /app

COPY build/libs/*.jar app.jar

# run app
ENTRYPOINT ["java", "-jar", "app.jar"]