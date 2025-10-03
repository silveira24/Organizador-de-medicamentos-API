# Organizador de Medicamentos - Backend

Bem-vindo(a) ao backend da aplicação **Organizador de Medicamentos**, desenvolvida em **Spring Boot** com **Java 21**. Esta API RESTful gerencia medicamentos e seus agendamentos de doses, servindo como base para a aplicação web de portfólio.

## 🚀 Tecnologias Utilizadas

* **Java 21**: Linguagem de programação principal.
* **Spring Boot**: Framework para o desenvolvimento rápido da aplicação.
* **Spring Data JPA**: Abstração para a camada de persistência.
* **PostgreSQL**: Sistema de gerenciamento de banco de dados relacional.
* **Lombok**: Ferramenta para reduzir o código boilerplate (getters, setters, etc.).
* **Maven**: Gerenciador de dependências e automação de build.

## ⚙️ Configuração do Ambiente

### Pré-requisitos

Certifique-se de que as seguintes ferramentas estão instaladas em sua máquina:
* JDK 21
* Maven
* PostgreSQL

### Configuração do Banco de Dados

1.  Crie um banco de dados PostgreSQL com o nome `organizador_db` (ou o nome que preferir).
2.  Abra o arquivo `src/main/resources/application.properties` e atualize as credenciais de acesso ao seu banco de dados:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/organizador_db
    spring.datasource.username=SEU_USUARIO_POSTGRES
    spring.datasource.password=SUA_SENHA_POSTGRES
    ```

## 🖥️ Como Executar a Aplicação

1.  Clone este repositório.
2.  Navegue até o diretório do projeto no terminal.
3.  Execute o seguinte comando para iniciar o servidor Spring Boot:

    ```bash
    mvn spring-boot:run
    ```
4.  A API estará disponível em `http://localhost:8080`.

## 📖 Endpoints da API

A API segue o padrão REST e utiliza os seguintes endpoints para gerenciamento de recursos:

### Medicamentos

* **`POST /api/medicamentos`**: Cria um novo medicamento e gera seus agendamentos futuros.
* **`GET /api/medicamentos`**: Lista todos os medicamentos ativos.
* **`GET /api/medicamentos/{id}`**: Retorna um medicamento específico por ID.
* **`PUT /api/medicamentos/{id}`**: Atualiza os dados de um medicamento.
* **`DELETE /api/medicamentos/{id}`**: Desativa um medicamento (exclusão lógica).

### Agendamentos de Doses

* **`GET /api/agendamentos?data=YYYY-MM-DD`**: Retorna todos os agendamentos de um dia específico.
    * **Filtros Opcionais:** Use `?status=PENDENTE` ou `?status=TOMADA` para filtrar.
* **`PUT /api/agendamentos/{id}/tomada`**: Marca um agendamento como "tomado".
* **`GET /api/agendamentos/medicamento/{id}/futuros`**: Retorna os agendamentos futuros de um medicamento específico.

