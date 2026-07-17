# DOCUMENTAÇÃO

## TECNOLOGIAS UTILIZADAS
- Java | Spring Boot
- PostgreSQL
- Docker

## FLUXO DO PROGRAMA
O código abre os arquivos "candidatos.txt" e "concursos.txt" 
para leitura, cria os objetos Candidato e Concurso e os salva no banco de dados. Dois
metodos sao responsaveis por implementar as buscas definidas na explicacao do desafio. Esses metodos
estao disponiveis na classe AppController, que abre uma conexao HTTP na url http://localhost:8080/api.

Para retornar a busca numero 1: http://localhost:8080/api/{STRING_CPF}/concursos.

Para retornar a busca numero 2: http://localhost:8080/api/{STRING_CODIGO}/candidatos.

## COMO RODAR
```bash
docker compose up -d --build
```

Isso sobe a imagem do banco para o container e executa a aplicacao com:
```bash
mvn clean package -DskipTests
```

## CONSIDERACOES DE IMPLEMENTACAO


