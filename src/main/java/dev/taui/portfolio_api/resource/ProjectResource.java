package dev.taui.portfolio_api.resource;

import dev.taui.portfolio_api.model.Project;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/api/projects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Projects", description = "Endpoints de gerenciamento e consulta dos projetos do portfólio de Taui Silva")
public class ProjectResource {

    private static final List<Project> PROJECTS = List.of(
            new Project(
                    "nbx-stream",
                    "NBX-Stream",
                    "Plataforma fullstack de streaming e gestão de dados",
                    "Sistema fullstack robusto construído com Spring Boot, Angular e Docker, demonstrando arquitetura limpa e alta escalabilidade.",
                    List.of("Java", "Spring Boot", "Angular", "Docker"),
                    "https://github.com/tauisilva/nbx-stream",
                    null,
                    null,
                    true
            ),
            new Project(
                    "campus-connect",
                    "Campus Connect",
                    "Plataforma de mensagens e comunidade acadêmica em tempo real",
                    "Arquitetura de microsserviços orientada a eventos com Spring Boot, Angular, WebSockets e Docker.",
                    List.of("Spring Boot", "Angular", "WebSockets", "Docker"),
                    "https://github.com/cc-tcc-udf/message-app",
                    "https://github.com/cc-tcc-udf/message-api",
                    null,
                    true
            ),
            new Project(
                    "portfolio",
                    "Portfolio Fullstack (Cloud-Native)",
                    "Portfólio de alta performance com Quarkus 3 REST API e Angular 19 SPA",
                    "Arquitetura moderna dividida em SPA reativa com Tailwind CSS (Dark/Light mode) e API reativa em Quarkus 3 (Supersonic Subatomic Java) com Swagger UI.",
                    List.of("Quarkus", "Java", "Angular", "Tailwind CSS", "Docker"),
                    "https://github.com/tauisilva/Portfolio-app",
                    "https://github.com/tauisilva/Portfolio-api",
                    null,
                    true
            ),
            new Project(
                    "swagger-dark-theme",
                    "Swagger Dark Theme for Spring Boot",
                    "Personalização nativa do Swagger UI no Spring Boot (100% Java)",
                    "Ferramenta para desenvolvedores com tema escuro customizado e artigo técnico publicado no DEV Community.",
                    List.of("Java", "Spring Boot", "OpenAPI", "CSS"),
                    "https://github.com/tauisilva/Swagger-dartk-spring-boot",
                    null,
                    "https://dev.to/tauisilva/personalizando-o-swagger-ui-no-spring-boot-como-aplicar-um-tema-escuro-22b7",
                    true
            ),
            new Project(
                    "converter",
                    "Currency Converter",
                    "Conversor de moedas reativo com CI/CD automatizado e testes unitários",
                    "SPA desenvolvida em Angular com deploy contínuo no GitHub Pages via GitHub Actions e cobertura de testes automatizados com Karma/Chrome.",
                    List.of("Angular", "TypeScript", "GitHub Actions", "Karma"),
                    "https://github.com/tauisilva/Converter",
                    null,
                    "https://tauisilva.github.io/Converter/",
                    true
            )
    );

    @GET
    @Operation(summary = "Listar todos os projetos", description = "Retorna a lista completa de projetos em destaque do portfólio.")
    public List<Project> listAll() {
        return PROJECTS;
    }

    @GET
    @Path("/featured")
    @Operation(summary = "Listar projetos em destaque", description = "Retorna apenas os projetos marcados como featured.")
    public List<Project> listFeatured() {
        return PROJECTS.stream().filter(Project::featured).toList();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Buscar projeto por ID", description = "Retorna os detalhes de um projeto específico através do seu identificador.")
    public Response getById(@PathParam("id") String id) {
        return PROJECTS.stream()
                .filter(p -> p.id().equalsIgnoreCase(id))
                .findFirst()
                .map(Response::ok)
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
                .build();
    }
}
