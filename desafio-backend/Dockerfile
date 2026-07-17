# Estágio de build
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copia o pom.xml e baixa as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte e compila
COPY src ./src
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
