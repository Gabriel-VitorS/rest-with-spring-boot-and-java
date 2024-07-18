package br.com.gabrielvitors.rest_with_spring_boot_and_java.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Value("${cors.originPatterns:default}")
    private String corOriginPatterns = "";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var allowedOrigins = corOriginPatterns.split(",");

        registry.addMapping("/**")
//              .allowedMethods("*")
                .allowedMethods("GET", "POST", "PUT")
                .allowedOrigins("")
                .allowCredentials(true);
    }
}
