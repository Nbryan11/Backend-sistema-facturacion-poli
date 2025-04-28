package com.example.practicaspring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Ajusta la ruta según tus necesidades
                .allowedOrigins("https://yellow-beach-06a63ec1e.5.azurestaticapps.net",
                        "http://localhost:5173")    // Cambia "*" por dominios específicos si es necesario
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
