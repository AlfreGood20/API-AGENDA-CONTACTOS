package com.api.api_contacto.dtos.request;

import com.api.api_contacto.utils.CategoriaContacto;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContactoRequest {

    private String nombre;

    private String apellidos;

    private String telefono;

    private String correo;

    private String direccion;

    @NotNull(message = "La categoria no puede ser nula. Eliga entre Familia, Escuela, Amigo, Negocio, Novia, Novio, Ex o Amante")
    private CategoriaContacto categoria;

    @JsonProperty("color_avatar")
    private String colorAvatar;
}