# Usa la imagen de OpenJDK 21
FROM openjdk:21-jdk-slim

# Define el directorio de trabajo
WORKDIR /app

# Copia todo el proyecto
COPY . .

# Construye la aplicación desde el módulo app_service y crea el JAR
RUN ./gradlew :applications:app_service:bootJar

# Expone el puerto 8080 para la aplicación
EXPOSE 8080

# Ejecuta el archivo JAR generado
CMD ["java", "-jar", "applications/app_service/build/libs/app_service-0.0.1-SNAPSHOT.jar"]
