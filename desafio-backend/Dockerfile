# Estágio de build
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copia o pom.xml e baixa as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia os arquivos txt necessários para os testes
COPY candidatos.txt concursos.txt ./

# Copia o código-fonte
COPY src ./src

# Força a aplicação a usar o banco H2 em memória durante a fase de testes do build
ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:testdb
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.h2.Driver
ENV SPRING_DATASOURCE_USERNAME=sa
ENV SPRING_DATASOURCE_PASSWORD=

# Roda os testes e compila a aplicação
RUN mvn clean package

# Estágio de execução
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia os arquivos txt para que a aplicação consiga lê-los
COPY candidatos.txt concursos.txt ./

# Copia o arquivo .jar gerado no estágio de build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
