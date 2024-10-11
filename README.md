## Instalação e Execução

### Clonando o repositório

````bash
git clone https://github.com/seu-usuario/seu-projeto.git
cd seu-projeto


# Simple API Java

Esta é uma API simples construida em Java, que tem como objetivo fornecer uma base para o desenvolvimento de serviços RESTful.A API inclui testes unitários, documentação automática com OpenAPI, e é facilmente executável em um ambiente Docker.

## Pré-requisitos

- Java 21
- Git
- Docker

## Build e execução

```sh
docker compose up --build
````

## Testes unitários (validação)

./mvnw test

## Documentação online (OpenAPI)

http://localhost:8080/swagger-ui/index.html

![](/assets/images/swagger.png)
