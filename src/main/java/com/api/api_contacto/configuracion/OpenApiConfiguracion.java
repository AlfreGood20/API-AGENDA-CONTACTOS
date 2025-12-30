package com.api.api_contacto.configuracion;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@SecurityScheme(
    name = "Bearer Authentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)

@OpenAPIDefinition(

    info = @Info(
        title = "API REST FULL - CONTACTO",
        description = "Esta api fue construido para usarse en un front end, que se encuentra en mi repositorio de github. Pequeña api para uso educativos y reforzamiento de conocimientos adquiridos durante la practica.",
        contact = @io.swagger.v3.oas.annotations.info.Contact(
            name = "José Alfredo López De La Cruz",
            email = "josealfredolopezdelacruz2@gmail.com",
            url = "https://github.com/AlfreGood20"
        ),
        version = "2.0.0"
    ),
    servers = {
        @Server(
            url = "http://localhost:8080/",
            description = "Servidor localmente"
        )
    }
)

public class OpenApiConfiguracion {
}