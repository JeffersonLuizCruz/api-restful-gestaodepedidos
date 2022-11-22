<h1 align="center">
    <img alt="Ecommerce" src="https://github.com/JeffersonLuizCruz/api-restful-gestaodepedidos/blob/main/API-RESTful-GestaoDePedidos/src/main/resources/tamplate/logo3.png" />
</h1>

<h3 align="center">
  REST API HelpDesk - BackEnd - Spring Boot
</h3>

<p align="center">Aplicação simples Help Desk de controle de chamados.</p>

![GitHub repo size](https://img.shields.io/github/repo-size/JeffersonLuizCruz/financial)  ![Packagist License](https://img.shields.io/packagist/l/JeffersonLuizCruz/financial)  ![GitHub top language](https://img.shields.io/github/languages/top/JeffersonLuizCruz/financial)  ![GitHub language count](https://img.shields.io/github/languages/count/JeffersonLuizCruz/financial?label=Linguagem%20de%20Programa%C3%A7%C3%A3o)  ![GitHub followers](https://img.shields.io/github/followers/JeffersonLuizCruz?style=social)

<p align="center">
  <a href="#-sobre">Sobre o projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-diagrama">Diagrama de Classe</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-links">Links</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-contato">Contato</a>
</p>

## :page_with_curl: Sobre o projeto <a name="-sobre"/></a>

> Essa aplicação é uma base para construção de um sistema de Help Desk. O foco é garantir o nível de permissão de cada usuário dentro do sistema. O administrador é o único capaz alterar o estágio de um chamado e também encerrá-lo. Enquanto um usuário simples somente cria um chamado e faz consulta. 

O objetivo da aplicação é registrar os chamados solicitados pelo usuário e ter o controle desses chamados. O administrador da aplicação é o único capaz de alterar o estágio de um chamado. Os estágios de um chamado são: Chamado Aberto(OPEN), Chamado em Andamento(IN PROGRESS) e Finalizado(CLOSED). O usuário comum somente cria e consulta seus chamados e somente o administrador logado na aplicação pode fazer qualquer alteração.


## :page_with_curl: Diagrama de Classe <a name="-diagrama"/></a>
<h1 align="center">
    <img alt="Ecommerce" src="https://github.com/JeffersonLuizCruz/API-RESTful-GestaoDePedidos/blob/main/API-RESTful-GestaoDePedidos/src/main/resources/tamplate/diagrama-class.png" />
</h1>

## Tecnologia:
- [x] Java 11<br>
- [x] Spring Boot 2.4.4<br>
- [x] Spring Data - JPA/Hibernate<br>
- [x] Banco de Dados PostgreSQL<br>
- [x] Spring Secutity - OAuth 2<br>
- [ ] Front-end Angular<br>
- [ ] Implementação no Heroku<br>
- [ ] Amazon S3<br>

## Construção do Projeto:
- [x] Criação de Interface Service (garantir baixo acoplamento)<br>
- [x] CRUD (ORM Hibernate - Ambiente de teste)<br>
- [x] Exception Personalizada
- [x] DTO (Aplicação DTO na camada de Controller)
- [x] Consulta e Busca Paginada
- [x] Autenticação e Autorização (JWT)

## Start da Aplicação
```
server.error.include-stacktrace=never

spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/gestao_de_pedidos
spring.datasource.username=postgres
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

#Valores nulos(null) serão ignorados na resposta json.
spring.jackson.default-property-inclusion= non-null
```

### Recurso User

<details>
<summary><strong> Endpoint Usuário(/users/**)</strong></summary>

### Rota[POST]
- Salvar Usuário.
[POST] http://localhost:8080/users/

#### Body:
```
{
    "name": "Hugo",
    "email": "hugo@gmail.com",
    "password": "123456789",
    "role": "ADMINISTRATOR"
}
```

### Rota[POST]
- Login e Senha.
[POST] http://localhost:8080/users/login

#### Body:
```
{
    "email": "hugo@gmail.com",
    "password": "123456789"
}
```
### Reponse Token JWT:
```
{
    "token": "eyJhbGciOiJIUzUxMiJ9.
    eyJzdWIiOiJodWdvQGdtYWlsLmNvbSIsImV4cCI6MTYxMzkxODcxMCwicm9sZSI6WyJST0xFX0FETUlOSVNUUkFUT1IiXX0.
    hAFvi5vIQq_SN6_hb4GBx2bvKWCZrV5hxpc9R6en7EenoFlH35UU3S0bim0kfLXKluRwR4y3lwM4LKGrovAcLA",
    
    "expire": 1613918710023,
    "tokenProvider": "Dearer"
}
```

### Rota[PUT]
- Editar Usuário.
[PUT] http://localhost:8080/users/1

#### Body:
```
{
    "name": "Hugo",
    "email": "hugo@gmail.com",
    "password": "1234hugo", //Edite Password
    "role": "ADMINISTRATOR"
}
```

### Rota[GET]
- Listar Usuário por id .
[GET] http://localhost:8080/users/1

#### Response:
```
{
    "id": 1,
    "name": "Hugo",
    "email": "hugo@gmail.com",
    "role": "ADMINISTRATOR"
}
```
### Rota[GET]
- Lista Páginada de Usuários
[GET] http://localhost:8080/users/

#### Response:
```
{
    "totalElements": 10,
    "pageSize": 10,
    "totalPages": 1,
    "elements": [
        {
            "id": 1,
            "name": "hugo",
            "email": "hugo@gmail.com",
            "role": "ADMINISTRATOR"
        },
        {
            "id": 2,
            "name": "jeff",
            "email": "jeff@gmail.com",
            "role": "SIMPLE"
        },
} ...
```
### Rota[GET]
- Lista Páginada de um Pedido de respectivo usuário.
[GET] http://localhost:8080/users/1/requests

#### Response:
```
{
    "totalElements": 1,
    "pageSize": 10,
    "totalPages": 1,
    "elements": [
        {
            "id": 1,
            "subject": "Pedido de Notebook",
            "description": "Notebook Acer de configuraÃ§Ã£o avanÃ§ada para programaÃ§Ã£o Android",
            "creationDate": "2021-02-16T15:23:28.464+00:00",
            "state": "OPEN",
            "owner": {
                "id": 1,
                "name": "hugo",
                "email": "hugo@gmail.com",
                "role": "ADMINISTRATOR"
            }
        }
    ]
}
```
</details>

### Recurso Requests

<details>
<summary><strong> Endpoint Pedido(/requests/**)</strong></summary>
    
### Rota[POST]
- Cadastro de Pedido.
[POST] http://localhost:8080/requests

#### Body:
```
{
    "subject": "Pedido de Notebook",
    "description": "Notebook Acer de configuração avançada para programação Android",
    "owner": {
        "id": 1,
        "name": "Hugo",
        "email": "hugo@gmail.com",
        "role": "ADMINISTRATOR"
    }
}
```
### Rota[PUT]
- Editar Pedido.
[POST] http://localhost:8080/requests/1

#### Body:
```
{
    "subject": "Pedido de Notebook",
    "description": "Notebook Dell de configuração avançada para programação Android", // Editado marca de notebook
    "owner": {
        "id": 1,
        "name": "Hugo",
        "email": "hugo@gmail.com",
        "role": "ADMINISTRATOR"
    }
}
```
### Rota[GET]
- Listar Pedido por id .
[GET] http://localhost:8080/requests/1

#### Response:
```
{
    "id": 1,
    "subject": "Pedido de Notebook",
    "description": "Notebook Dell de configuraÃ§Ã£o avanÃ§ada para programaÃ§Ã£o Android",
    "creationDate": "2021-02-16T15:23:28.464+00:00",
    "state": "OPEN",
    "owner": {
        "id": 1,
        "name": "Hugo",
        "email": "hugo@gmail.com",
        "role": "ADMINISTRATOR"
    }
}
```
### Rota[GET]
- Listar de Pedido paginada .
[GET] http://localhost:8080/requests/

#### Response:
```
{
    "totalElements": 2,
    "pageSize": 10,
    "totalPages": 1,
    "elements": [
        {
            "id": 1,
            "subject": "Pedido de Notebook",
            "description": "Notebook Dell de configuraÃ§Ã£o avanÃ§ada para programaÃ§Ã£o Android",
            "creationDate": "2021-02-16T15:23:28.464+00:00",
            "state": "OPEN",
            "owner": {
                "id": 1,
                "name": "hugo",
                "email": "hugo@gmail.com",
                "role": "ADMINISTRATOR"
            }
        },
        {
            "id": 2,
            "subject": "Pedido de compra de Software IDE",
            "description": "O software IDE para desenvolvimento para compra é o Intellij",
            "creationDate": "2021-02-16T15:41:24.807+00:00",
            "state": "OPEN",
            "owner": {
                "id": 1,
                "name": "hugo",
                "email": "hugo@gmail.com",
                "role": "ADMINISTRATOR"
            }
        }
    ]
}
...
```

### Rota[GET]
- Listar de Pedido paginada com seu respectivo Estágio(status = OPEN | IN_PROGRESS | CLOSED).
[GET] http://localhost:8080/requests/1/request-stages

#### Response:
```
{
    "totalElements": 1,
    "pageSize": 10,
    "totalPages": 1,
    "elements": [
        {
            "id": 1,
            "description": "Pedido submetido para anÃ¡lise",
            "realizationDate": "2021-02-16T17:19:35.869+00:00",
            "state": "IN_PROGRESS",
            "request": {
                "id": 1,
                "subject": "Pedido de Notebook",
                "description": "Notebook Acer de configuraÃ§Ã£o avanÃ§ada para programaÃ§Ã£o Android",
                "creationDate": "2021-02-16T15:23:28.464+00:00",
                "state": "IN_PROGRESS",
                "owner": {
                    "id": 1,
                    "name": "hugo",
                    "email": "hugo@gmail.com",
                    "role": "ADMINISTRATOR"
                }
            },
            "owner": {
                "id": 1,
                "name": "hugo",
                "email": "hugo@gmail.com",
                "role": "ADMINISTRATOR"
            }
        }
    ]
}
```
</details>

### Recurso Request-Stages

<details>
<summary><strong> Endpoint Estágio deo Pedido(OPEN | IN_PROGRESS | CLOSED)(/request-stages/**)</strong></summary>
    
### Rota[POST]

- O administrador do sistema irá definir o estatus do cadastro do Pedido do Cliente.
[POST] http://localhost:8080/request-stages
#### Body:
```
{
    "id": 1,
    "description": "Pedido submetido para anÃ¡lise",
    "realizationDate": "2021-02-16T17:19:35.869+00:00",
    "state": "IN_PROGRESS",
    "request": {
        "id": 1,
        "subject": "Pedido de Notebook",
        "description": "Notebook Acer de configuraÃ§Ã£o avanÃ§ada para programaÃ§Ã£o Android",
        "creationDate": "2021-02-16T15:23:28.464+00:00",
        "state": "IN_PROGRESS",
        "owner": {
            "id": 1,
            "name": "Hugo",
            "email": "hugo@gmail.com",
            "role": "ADMINISTRATOR"
        }
    },
    "owner": {
        "id": 1,
        "name": "Hugo",
        "email": "hugo@gmail.com",
        "role": "ADMINISTRATOR"
    }
}
...
 ```   
</details>


