# AGENTS.md — Engineering Guidelines for Portfolio-api

This document establishes the official engineering guidelines, architectural rules, code quality standards, and development workflows for **Portfolio-api** (Quarkus 3.39+ on Java 21+). Any AI agent, contributor, or developer modifying this repository MUST strictly comply with these rules.

---

## 1. Universal English Rule (Strict)

- **Language of Code**: All source code, identifiers, and development artifacts MUST be written exclusively in **English**:
  - Class names, records, interfaces, enums, methods, fields, parameters, and local variables.
  - Package names and file names (e.g., `ProjectResource.java`, `Project.java`).
  - Code comments, Javadocs, and commit messages.
  - Git branch names and PR descriptions.
  - Log messages and internal technical exception messages.
- **Localized Messages Exception**: Any user-facing response message localized for Portuguese or Spanish MUST live strictly in the i18n Resource Bundles (`messages_pt_BR.properties`, `messages_es.properties`), never hardcoded in Java classes.

---

## 2. Modern Java 21+ Standards

### 2.1. Language Features & Immutability
- **Records for Data Carriers**: Use Java `record` for all DTOs, query projections, API request/response payloads, and immutable value objects. Avoid mutable POJOs unless required by ORM entities.
- **Pattern Matching & Switch Expressions**: Prefer pattern matching for `instanceof` and modern exhaustive `switch` expressions over chained `if-else` blocks:
  ```java
  return switch (status) {
      case ACTIVE -> handleActive();
      case ARCHIVED -> handleArchived();
  };
  ```
- **Avoid `null` (Use `Optional<T>`)**:
  - Never return `null` from public methods or repository lookups.
  - Return `Optional<T>` for single optional values.
  - Return empty collections (`List.of()`, `Collections.emptyList()`) instead of `null` or `Optional<List<T>>`.
- **Virtual Threads & Non-blocking I/O**: Quarkus REST is reactive and non-blocking by default. For blocking calls or database access with Panache, annotate endpoints with `@RunOnVirtualThread` or ensure worker thread execution.

---

## 3. Quarkus 3.39 Architecture & CDI Guidelines

### 3.1. Quarkus REST & Panache
- **Quarkus REST (formerly RESTEasy Reactive)**: Always use standard JAX-RS / Quarkus REST annotations:
  - `@Path`, `@GET`, `@POST`, `@PUT`, `@DELETE`
  - `@Produces(MediaType.APPLICATION_JSON)`
  - `@Consumes(MediaType.APPLICATION_JSON)`
- **Hibernate ORM with Panache**:
  - For simple entities, extend `PanacheEntityBase` or `PanacheEntity`.
  - For domain-driven, decoupled architecture, prefer the **Panache Repository Pattern** (`PanacheRepository<Entity>`), keeping entities focused on domain modeling and repositories focused on persistence queries.
- **Dependency Injection (CDI / Arc)**:
  - Use `@ApplicationScoped` for stateless services, resources, and repositories.
  - Prefer constructor injection (clean, testable, and final fields) or field injection with `@Inject`.

### 3.2. GraalVM Native Image Readiness
- Keep code free of unindexed dynamic reflection, dynamic class loading, or thread-group mutations that violate GraalVM AOT (Ahead-of-Time) compilation.
- Register DTOs and models with `@RegisterForReflection` if they are serialized dynamically without direct compile-time references.

---

## 4. Clean Architecture & Package Structure

The backend follows domain-driven separation of concerns into distinct architectural packages:

```text
src/main/java/dev/taui/portfolio_api/
├── resource/              # REST Entrypoints / JAX-RS Controllers (HTTP handling, OpenAPI)
├── service/               # Core business logic, use cases, domain orchestrators
├── model/                 # Pure domain entities, value objects, and records
│   ├── dto/               # Request and response transfer objects (Records)
│   └── entity/            # JPA / Panache entities (if database-backed)
├── repository/            # Panache persistence repositories and query adapters
├── infra/                 # Cross-cutting infrastructure concern adapters
│   ├── exception/         # Exception mappers, RFC 7807 problem details
│   ├── filter/            # HTTP logging, correlation ID filters
│   └── config/            # Declarative configuration classes
└── resources/
    ├── application.properties # Quarkus configuration
    └── i18n/
        ├── messages.properties       # Default (English) messages
        ├── messages_pt_BR.properties # Brazilian Portuguese messages
        └── messages_es.properties    # Spanish messages
```

### 4.1. Layering Rules
- **Resources (`resource/`)**: Must NOT contain business rules or direct SQL/HQL queries. They validate input, delegate to services, and map to HTTP responses.
- **Services (`service/`)**: Contain domain invariants and business logic. They are framework-agnostic where possible.
- **Repositories (`repository/`)**: Encapsulate all database interaction and Panache queries.

---

## 5. Code Quality & SonarQube Compliance (Java Quality Gates)

To prevent code smells, bugs, security hotspots, and quality gate failures:

### 5.1. Complexity & Size Thresholds
- **Cognitive Complexity**: Maximum **15** per method. Break down deep branching, loops, and nested logic into small, expressive private helper methods.
- **Cyclomatic Complexity**: Maximum **10** per method.
- **Method Length**: Maximum **30 lines** per method. Each method must have a single level of abstraction and follow the Single Responsibility Principle (SRP).
- **Class Length**: Maximum **300 lines** per file. Decompose large classes into cohesive services.
- **Parameter Count**: Maximum **3 parameters** per method. Use a typed `record` or parameter object for 4 or more parameters.

### 5.2. Clean Code Rules
- **No Generic Catch**: Never catch `Throwable` or raw `Exception`. Catch specific checked/runtime exceptions.
- **No `System.out.println`**: Always use JBoss Logging / SLF4J:
  ```java
  private static final Logger LOG = Logger.getLogger(ProjectResource.class);
  LOG.infof("Fetching project with id: %s", id);
  ```
- **No Hardcoded Literals (DRY)**: Centralize constants, media types, and configuration keys into constants or enums.
- **Proper Resource Cleanup**: Use `try-with-resources` for any closable resource (`AutoCloseable`).

---

## 6. Global Error Handling (RFC 7807 Problem Details)

- All unexpected errors and domain exceptions must be transformed into standard HTTP problem details via JAX-RS `ExceptionMapper`:
  ```json
  {
    "type": "https://api.taui.dev/errors/project-not-found",
    "title": "Project Not Found",
    "status": 404,
    "detail": "Project with identifier 'abc' was not found.",
    "instance": "/api/projects/abc",
    "timestamp": "2026-09-07T20:15:00Z"
  }
  ```
- Never leak internal stack traces or database schema details to clients in production mode.

---

## 7. Internationalization (i18n)

The API supports multilingual response messages based on the client's `Accept-Language` header:
1. **English (`en`)** — Default.
2. **Portuguese (`pt-BR`)**.
3. **Spanish (`es`)**.

### 7.1. Message Bundles
- Localized validation messages and error details must be defined in `src/main/resources/i18n/messages_{locale}.properties`.
- Use Quarkus `@MessageBundle` or standard `ResourceBundle` to resolve messages dynamically according to the incoming request's locale.

---

## 8. OpenAPI & Swagger Documentation Standards

Every REST resource and endpoint MUST be comprehensively documented:
- **`@Tag`**: Class-level categorization of the resource.
- **`@Operation`**: Summary and description of what the endpoint accomplishes.
- **`@APIResponses`**: Explicit HTTP status codes (`200 OK`, `201 Created`, `400 Bad Request`, `404 Not Found`, `500 Internal Server Error`).
- Swagger UI is published at `/q/swagger-ui/` and schema at `/q/openapi`.

---

## 9. Testing & Quality Verification

- **Integration Testing**: Use `@QuarkusTest` combined with `io.restassured.RestAssured` to test end-to-end HTTP contracts.
- **Unit Testing**: Test domain services and logic with JUnit 5 and Mockito if mocking is required.
- **Pre-commit Verification Checklist**:
  ```powershell
  # Compile and verify static bytecode
  .\mvnw clean compile

  # Run full test suite with RestAssured
  .\mvnw test

  # Verify package distribution
  .\mvnw package -DskipTests
  ```
