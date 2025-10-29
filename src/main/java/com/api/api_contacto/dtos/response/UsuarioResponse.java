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
public class UsuarioResponse {
    
    private long id;

    private String nombre;
  
    private String apellidos;

    private String telefono;
 
    private String correo;

    @JsonProperty("fecha_registro")
    private String fechaRegistro;
}