package com.api.api_contacto.configuracion;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfiguracion {

    @Bean
    public OpenAPI infoDocumentacion(){
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080/");
        localServer.description("Servidor URL local");

        Server proServer = new Server();
        proServer.setUrl("https://");
        proServer.description("Servidor URL producción");

        Contact contacto = new Contact();
        contacto.setName("José Alfredo López De La Cruz");
        contacto.setEmail("josealfredolopezdelacruz2@gmail.com");
        contacto.setUrl("https://github.com/AlfreGood20");

        Info informacion = new Info();
        informacion.setTitle("API REST FULL - CONTACTO");
        informacion.setDescription("Esta api fue construido para usarse en un front end, que se encuentra en mi repositorio de github. Pequeña api para uso educativos y reforzamiento de conocimientos adquiridos durante la practica.");
        informacion.setContact(contacto);
        informacion.setVersion("1.0.0");

        return new OpenAPI().info(informacion).servers(List.of(localServer,proServer));
    }
}