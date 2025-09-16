
# Imagen base con JDK 21
FROM eclipse-temurin:21-jdk

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y el código fuente
COPY pom.xml .
COPY src ./src

# Instalar Maven y compilar el proyecto
RUN apt-get update &&     apt-get install -y maven &&     mvn clean package -DskipTests

# Copiar el archivo .jar generado
COPY target/certificaciones-sistema-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto (ajustar si usas otro)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
