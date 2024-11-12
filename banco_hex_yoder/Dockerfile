# Usa una imagen oficial de OpenJDK como base
FROM openjdk:21-jdk-slim

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el JAR generado en el contenedor
COPY applications/app_service/build/libs/app_service-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que la aplicación escucha
EXPOSE 8080

# Define el comando de inicio
CMD ["java", "-jar", "app.jar"]
