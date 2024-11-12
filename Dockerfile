# Imagen oficial de OpenJDK (Java 21)
FROM openjdk:21-jdk-slim

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia todos los archivos al contenedor
COPY . .

# Otorga permisos de ejecución a gradlew
RUN chmod +x ./gradlew

# Instala y construye la aplicación sin ejecutar pruebas
RUN ./gradlew clean build -x test --no-daemon

# Expone el puerto que usa Spring Boot (8080 por defecto)
EXPOSE 8080

# Ejecuta la aplicación
CMD ["java", "-jar", "build/libs/app_service-0.0.1-SNAPSHOT.jar"]
