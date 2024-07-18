package br.com.gabrielvitors.rest_with_spring_boot_and_java.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Curso Udemy")
                        .version("V1")
                        .description("")
                        .termsOfService("www.teste.com")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("www.teste.com")
                                )
                );
    }

}
