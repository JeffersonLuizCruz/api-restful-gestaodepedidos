# API-RESTful : Gestao De Pedidos
> Back-end Java feito com Spring Boot para criação de API REST... Logo em breve essa API terá integração com Angular.

Essa API foi feita para cadastrar chamados de pedidos. E cada usuário poderá acompanhar os estágios de cada pedido feito. Cada usuário tem sua autorização de acesso dentro da aplcação. Os administradores tem o poder de encerar os pedidos e alterar seus estágios(OPEM, IN_PROGRESS, CLOSED).

## Dependências
- Java 11
- Postgres
- Spring Boot 3.2.1
- JPA
- Spring Security
- JUnit 5

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
## Diagrama de Classe
![alt text](https://github.com/JeffersonLuizCruz/API-RESTful-GestaoDePedidos/blob/main/API-RESTful-GestaoDePedidos/src/main/resources/tamplate/diagrama-class.png)

## Recursos da cadama User

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
#### Reponse Token JWT:
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
