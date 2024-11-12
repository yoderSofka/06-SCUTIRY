FROM openjdk:21-jdk-slim
WORKDIR /app


COPY . .

# Instala Gradle Wrapper y construye la aplicación sin pruebas
RUN ./gradlew clean build -x test --no-daemon

# Expone el puerto que usa Spring Boot (8080 por defecto)
EXPOSE 8080

# Ejecuta la aplicación con `bootRun`
CMD ["./gradlew", "bootRun", "--no-daemon"]
