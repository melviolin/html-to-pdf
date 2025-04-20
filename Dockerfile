FROM openjdk:17-jdk-slim

# Instala Maven
RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY . /app

# Empaqueta el proyecto
RUN mvn clean package -DskipTests

EXPOSE 4567

CMD ["java", "-jar", "target/pdf-generator-1.0-jar-with-dependencies.jar"]
