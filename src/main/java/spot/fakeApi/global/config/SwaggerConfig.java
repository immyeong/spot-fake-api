package spot.fakeApi.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("all-api")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("SPOT FAKE-API")
                .version("v1.0.1")
                .description(
                        "<div style='text-align:center;'>"
                                + "<h2>WELCOME TO SPOT FAKE-API SERVER</h2>"
                                + "</div>"
                                + "<style>"
                                + "h1, h2 { color: #2c3e50; font-family: Arial, sans-serif; }"
                                + "p { font-size: 14px; }"
                                + "</style>"
                );

        return new OpenAPI()
                .addServersItem(new Server().url("https://ilmatch.net")
                        .description("Default Server URL"))
                .addServersItem(new Server().url("http://localhost:8080")
                        .description("Local Development Server"))
                .info(info);
    }
}
