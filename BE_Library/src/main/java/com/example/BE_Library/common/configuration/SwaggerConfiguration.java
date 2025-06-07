package com.example.BE_Library.common.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.Getter;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Configuration
public class SwaggerConfiguration {
    @Value("classpath:swagger/ApiCommon.md")
    private Resource apiCommon;

    @Value("${springdoc.serverUrl}")
    private String serverUrl;

    @Bean
    public OpenAPI openAPI() {
        String description = readDescription(apiCommon);
        Server server = new Server();
        server.setUrl(serverUrl);
        return new OpenAPI()
                .info(new Info().title("Library API")
                        .description(description)
                        .version("v0.1.0"))
                .servers(List.of(server))
                .addSecurityItem(new SecurityRequirement().addList("Token-Security"))
                .components(new Components().addSecuritySchemes("Token-Security",
                        new SecurityScheme().name("Token-Security").type(SecurityScheme.Type.HTTP).scheme("bearer")));
    }

    private String readDescription(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static final Set<String> WEB_API_TAGS = Set.of("User"
    );

    @Bean
    public GroupedOpenApi webApiGroup() {
        return GroupedOpenApi.builder()
                .group("Web API")
                .addOpenApiCustomizer(openApiCustomizer(WEB_API_TAGS))
                .build();
    }

    private OpenApiCustomizer openApiCustomizer(Set<String> allowTags) {
        return openApi -> {
            Paths paths = openApi.getPaths();
            paths.forEach((path, pathItem) ->
                    pathItem.readOperationsMap().forEach((method, operation) -> {
                        if (!filterAllowedTag(operation, allowTags)) {
                            pathItem.operation(method, null);
                        }
                    }));
        };
    }

    private boolean filterAllowedTag(Operation operation, Set<String> allowTags) {
        List<String> tags = operation.getTags();
        List<String> allowedTags = new ArrayList<>();
        boolean isContainAllowedTag = false;
        for (String tag : tags) {
            if (allowTags.contains(tag)) {
                isContainAllowedTag = true;
                allowedTags.add(tag);
            }
        }
        operation.setTags(allowedTags);
        return isContainAllowedTag;
    }
}
