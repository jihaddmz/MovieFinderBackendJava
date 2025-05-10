# Use a lightweight JDK base image
FROM eclipse-temurin:17-jdk

# Set the working directory
WORKDIR /app

# Copy the JAR file into the image
COPY target/MovieFinderBackendJava-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Render sets PORT env variable but it's good practice)
EXPOSE 8080

# Let Spring Boot bind to the port Render sets
ENV PORT=8080

RUN apt-get update && apt-get install -y ca-certificates

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
