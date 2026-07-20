# DOCUMENTAÇÃO

## TECNOLOGIAS UTILIZADAS
- Java | Spring Boot
- PostgreSQL
- Docker

## FLUXO DO PROGRAMA
O código abre os arquivos "candidatos.txt" e "concursos.txt" para leitura, 
cria os objetos Candidato e Concurso e os salva no banco de dados. 
Dois métodos são responsáveis por implementar as buscas definidas na 
explicação do desafio. Esses métodos estão disponíveis na classe AppController, 
que abre uma conexão HTTP na URL http://localhost:8080/api.

Para retornar a busca número 1: http://localhost:8080/api/{STRING_CPF}/concursos.

Para retornar a busca número 2: http://localhost:8080/api/{STRING_CODIGO}/candidatos.

## COMO RODAR
Abrir o terminal dentro da pasta desafio-backend e rodar o comando:
```bash
docker compose up -d --build
```
Isso sobe a imagem do PostgreSQL para o container e executa a aplicação Maven (inclusive os testes).

Para acompanhar os logs da aplicação Spring Boot:
```bash
docker logs -f spring_app
```

## DIFERENCIAIS 
- **Criar um serviço com o problema:** O programa não possui um frontend definido mas criei um RestController para atender requisições HTTPS
  (acredito que tenha sido o suficiente)
- **Utilizar banco de dados:** Utilizei PostgreSQL para a aplicação e o H2 em memória para rodar os testes
- **Implementar Clean Code e Padrão de programação da linguagem:** Implementado encapsulamento, 
nomes de métodos e variáveis seguindo convenção camel case, classes com responsabilidades únicas, divisão em camadas (service, controller, repository), etc.
- **Qualidade de Código com SonarQube:** O código foi verificado com SonarQube.
- **Implementar testes unitários:** Pela complexidade baixa do programa foram implementados poucos testes.
- **Implementar integração com Github Action:** Implementado, pasta .github/workflow.
- **Implementar usando Docker:** Implementado Dockerfile e docker-compose.

## CONSIDERAÇÕES SOBRE A IMPLEMENTAÇÃO
- Durante a execução do projeto, percebi 
que havia valores duplicados de CPF ou código dos concursos; por 
consequência, ao buscar alguma dessas entidades por CPF ou código, 
mais de uma entidade me era retornada. Assumi que não deveriam existir 
valores duplicados e impedi o salvamento de concursos/candidatos com o mesmo código/CPF. Por exemplo: buscando com CTRL-F no arquivo 
concursos.txt o valor de código: 13385322316, existem 8 concursos com esse mesmo código.

- Formatei o CPF de modo a deixar apenas os números. Logo, a busca pela URL da API deve conter apenas os números do CPF, sem pontos e traços.

## MEUS CONTATOS
- LINKEDIN: www.linkedin.com/in/bruno-vale-lourenco
- EMAIL: brvale05@gmail.com



