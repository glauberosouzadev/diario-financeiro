# Diario Financeiro

## Principais tecnologias
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **Spring boot validation**
- **Mysql**
-  **Docker**
-  **Kafka**

Esse projeto tem como objetivo organizar as transações feitas no mês, funcionando como um controle financeiro pessoal. A ideia é centralizar tudo em um único lugar de forma simples e eficiente.
  
User:

    Atributos: Name, Email.
    Funcionalidade: Cadastro de novos usuários.

Transaction:

    Atributos: TransactionType, Value, Data, category, description.
    Funcionalidade: Registro de transações feitas no mês ou em um período específico

```mermaid
classDiagram
  class User {
    - name: string
    - email: string
  }
  class Transaction {
    - TransactionType: ENUM
    - value: Bigdecimal
    - data: LocalDate
    - category: string
    - description: string
  }
```
## Funcionalidades do Sistema

- Operações CRUD (Criar, Listar, Atualizar e Deletar) para usuários e transações
- Os dados ficam salvos em um banco MySQL
- Sempre que uma nova transação é registrada, o sistema envia um evento para uma fila Kafka
  - Quando esse evento é processado, o sistema procura pelo usuário relacionado e dispara um e-mail com os detalhes da transação (em desenvolvimento)
 
## Projeto em andamento sujeito a alterações.
