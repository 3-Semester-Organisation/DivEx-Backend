# Stage 1: Build the application
FROM amazoncorretto:21 AS build

#when building the .jar file using the mvnw it needs tar which is a util
RUN yum install -y tar gzip

# Set working directory
WORKDIR /app

# Copying Maven files and source code into working directiory
COPY . .

# Build the application and create the .jar file
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the runtime image
FROM amazoncorretto:21

# Set working directory for runtime
WORKDIR /app

# Copy the built .jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Define the entry point
ENTRYPOINT ["java"]

CMD ["-jar", "app.jar"]

#ty gpt