# Use the JDK for the build stage
FROM eclipse-temurin:17-jdk as builder

WORKDIR /app

# 1. Copy Gradle wrapper and config files first (for cache)
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
# Copy the source code
COPY src src

# Make gradlew executable and build the application
RUN chmod +x gradlew
# Render will set this environment variable during build
RUN ./gradlew clean build -x test

# 2. Final runtime stage - use JRE for smaller image
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Create a non-root user for security (important for Render)
RUN groupadd --system javauser && useradd --system --gid javauser javauser

# Copy the built JAR from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Change ownership to non-root user
RUN chown -R javauser:javauser /app
USER javauser

# Expose the port (Render will use this)
EXPOSE 8080

# Use ENTRYPOINT + CMD for better flexibility
ENTRYPOINT ["java", "-jar"]
CMD ["/app/app.jar"]