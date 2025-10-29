package com.api.api_contacto.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContactoResponse {

    private long id;

    private String nombre;

    private String apellidos;

    private String telefono;

    private String correo;

    private String direccion;

    @JsonProperty("color_avatar")
    private String colorAvatar;
    
    private Boolean favorito;
}