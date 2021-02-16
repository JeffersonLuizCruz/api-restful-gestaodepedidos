# API-RESTful : Gestao De Pedidos
> Back-end Java feito com Spring Boot para criação de API REST... Logo em breve essa API terá integração com Angular.

Essa API foi feita para cadastrar chamados de pedidos. E cada usuário poderá acompanhar os estágios de cada pedido feito. Cada usuário tem sua autorização de acesso dentro da aplcação. Os administradores tem o poder de encerar os pedidos e alterar seus estágios(OPEM, IN_PROGRESS, CLOSED).

## Dependências
- Java 11
- Mysql
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
![](/src/main/resources/tamplate/diagrama-class.png)
<img src=”/src/main/resources/tamplate/diagrama-class.png”>
