package dev.taui.portfolio_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
  info = @Info(
    title = "Portfolio API",
    version = "0.1 - Alpha",
    description = "Api backend for my portfolio"
  )
)
@SecurityScheme(
  name = "bearerAuth",
  description = "JWT auth",
  scheme = "bearer",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  in = SecuritySchemeIn.HEADER
)
public class PortfolioApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(PortfolioApiApplication.class, args);
  }

}
