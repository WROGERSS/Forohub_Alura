package com.aluracursos.forohub.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .components(new Components()
            .addSecuritySchemes("bearer-key",
            new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
            .info(new Info()
                        .title("ForoHub")
                        .description("""
                                Challenger final ForoHub Api Rest de Alura Latam del curso Backend con Spring Framework.
                                
                                Pasos para verificar su funcionamiento:
                                
                                1.Creación usuario.
                                
                                2.Iniciar sesión con tus datos del paso anterior.
                                
                                3.Obtener el token al hacer login y adicionarlo al campo AUTHORIZE para acceder a los servicios de los controladores que requieran autorización.
                                                                      
                                """)
            );
    }
}
