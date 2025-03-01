package dev.taui.portfolio_api.infra.swagger;

import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiOAuthProperties;
import org.springdoc.core.providers.ObjectMapperProvider;
import org.springdoc.webmvc.ui.SwaggerIndexTransformer;
import org.springdoc.webmvc.ui.SwaggerWelcomeCommon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public SwaggerIndexTransformer swaggerIndexTransformer(
    SwaggerUiConfigProperties swaggerUiConfig,
    SwaggerUiOAuthProperties swaggerUiOAuthProperties,
    SwaggerWelcomeCommon swaggerWelcomeCommon,
    ObjectMapperProvider objectMapperProvider) {
    return new SwaggerCodeBlockTransformer(swaggerUiConfig, swaggerUiOAuthProperties, swaggerWelcomeCommon, objectMapperProvider);
  }
}
