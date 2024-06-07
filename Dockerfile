FROM eclipse-temurin:17-jdk-alpine

# Copie o arquivo JAR para o contêiner
COPY /build/libs/*.jar app.jar

# Exponha a porta em que a aplicação será executada
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "/app.jar"]