# 🌐 Portfolio API

<div align="center">

![Java](https://img.shields.io/badge/Java_23-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_3.4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-CC0202?style=for-the-badge&logo=flyway&logoColor=white)
![Swagger](https://img.shields.io/badge/OpenAPI_Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

API RESTful corporativa desenvolvida para prover dados dinâmicos, métricas de projetos e serviços integrados ao ecossistema do meu portfólio profissional.

</div>

---

## 🔗 Frontend Web (Aplicação Consumidora)

Esta API foi projetada para alimentar a interface gráfica do portfólio profissional:

👉 **[Portfolio-app — Repositório do Frontend (Angular 19)](https://github.com/tauisilva/Portfolio-app)**

---

## 🛠️ Tecnologias & Arquitetura

- **Linguagem:** Java 23
- **Framework:** Spring Boot 3.4.3
- **Persistência:** Spring Data JPA / Hibernate
- **Banco de Dados:** PostgreSQL 16
- **Database Migrations:** Flyway
- **Segurança:** Spring Security
- **Documentação:** Springdoc OpenAPI / Swagger UI
- **Containerização:** Docker & Docker Compose
- **Build Tool:** Maven

---

## 🚀 Como Executar Localmente

### Pré-requisitos

- [JDK 23](https://adoptium.net/) instalado
- [Docker & Docker Compose](https://www.docker.com/) instalados

### 1. Clonar o repositório

```bash
git clone https://github.com/tauisilva/Portfolio-api.git
cd Portfolio-api
```

### 2. Subir o Banco de Dados com Docker

```bash
docker compose up -d
```

### 3. Executar a Aplicação

```bash
# Linux/macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

---

## 📖 Documentação da API (Swagger)

Com a aplicação em execução, acesse a interface interativa do Swagger UI em:
👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

---

## 👨‍💻 Autor

Desenvolvido por **Taui Silva Lima**
- GitHub: [@tauisilva](https://github.com/tauisilva)
- LinkedIn: [linkedin.com/in/tauisilva](https://www.linkedin.com/in/tauisilva/)
