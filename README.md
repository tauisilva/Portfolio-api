# 🌐 Portfolio API (Quarkus 3 Cloud-Native)

<div align="center">

![Java](https://img.shields.io/badge/Java_21%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Quarkus](https://img.shields.io/badge/Quarkus_3.39-4695EB?style=for-the-badge&logo=quarkus&logoColor=white)
![Panache](https://img.shields.io/badge/Hibernate_Panache-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Swagger UI](https://img.shields.io/badge/Swagger_UI-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

API RESTful reativa de altíssima performance desenvolvida em **Quarkus 3 (Supersonic Subatomic Java)**, projetada para servir dados dinâmicos, projetos e métricas com boot sub-segundo e baixo consumo de memória.

</div>

---

## 🔗 Frontend Web (Aplicação Consumidora)

Esta API alimenta a interface web do portfólio profissional:

👉 **[Portfolio-app — Repositório do Frontend (Angular 19)](https://github.com/tauisilva/Portfolio-app)**

---

## 🛠️ Tecnologias & Arquitetura

- **Runtime:** Quarkus 3.39.2 (Supersonic Subatomic Java)
- **Linguagem:** Java 21+
- **REST Engine:** Quarkus REST (Reativo e não-bloqueante) + Jackson
- **Persistência:** Hibernate ORM com Panache
- **Documentação & OpenAPI:** SmallRye OpenAPI + Swagger UI nativo com tema Dark
- **Observabilidade:** SmallRye Health (`/q/health`)
- **Compilação Nativa:** Preparado para GraalVM / Mandrel (Native Executable)
- **Containerização:** Docker (JVM e Native Image)
- **Build Tool:** Apache Maven 3.9

---

## 🚀 Como Executar Localmente

### Pré-requisitos

- [JDK 21+](https://adoptium.net/) instalado
- [Docker & Docker Compose](https://www.docker.com/) (opcional para Dev Services)

### 1. Clonar o repositório

```bash
git clone https://github.com/tauisilva/Portfolio-api.git
cd Portfolio-api
```

### 2. Executar em Modo de Desenvolvimento (Live Coding)

O Quarkus possui o revolucionário **Live Reload**: qualquer alteração no código é refletida instantaneamente sem reiniciar a JVM.

```bash
# Linux / macOS
./mvnw quarkus:dev

# Windows (PowerShell)
.\mvnw quarkus:dev
```

A API estará disponível em: `http://localhost:8080`

### 3. Executar o Pacote Compilado (JVM)

```bash
.\mvnw clean package -DskipTests
java -jar target/quarkus-app/quarkus-run.jar
```

---

## 📖 Documentação da API (Swagger UI) & Health

Com a aplicação em execução:
- **Swagger UI Interativo:** 👉 **[http://localhost:8080/q/swagger-ui/](http://localhost:8080/q/swagger-ui/)**
- **OpenAPI Schema (JSON):** `http://localhost:8080/q/openapi`
- **Health Checks (Liveness/Readiness):** `http://localhost:8080/q/health`

---

## ⚡ Endpoints Principais

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/api/projects` | Lista todos os projetos do portfólio |
| `GET` | `/api/projects/featured` | Lista apenas projetos em destaque |
| `GET` | `/api/projects/{id}` | Retorna detalhes de um projeto específico |
| `GET` | `/q/health` | Status de saúde da aplicação |

---

## 👨‍💻 Autor

Desenvolvido por **Taui Silva Lima**
- GitHub: [@tauisilva](https://github.com/tauisilva)
- LinkedIn: [linkedin.com/in/tauisilva](https://www.linkedin.com/in/tauisilva/)
