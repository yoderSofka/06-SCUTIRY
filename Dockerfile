# Usa una imagen de Java oficial
FROM openjdk:21-jdk-slim

# Crea un directorio de trabajo en el contenedor
WORKDIR /app

# Copia todo el contenido de tu proyecto al contenedor
COPY . .

# Da permisos de ejecución al archivo gradlew
RUN chmod +x ./gradlew

# Ejecuta Gradle para construir la aplicación sin pruebas y generar el JAR
RUN ./gradlew clean build -x test --no-daemon

# Cambia a la carpeta donde está el archivo JAR generado
WORKDIR /app/applications/app_service/build/libs

# Expone el puerto que usa la aplicación (8080 por defecto)
EXPOSE 8080

# Define el comando para ejecutar la aplicación
CMD ["java", "-jar", "app_service-0.0.1-SNAPSHOT.jar"]
