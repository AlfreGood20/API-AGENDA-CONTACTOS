package com.api.api_contacto.dtos.update;

import com.api.api_contacto.utils.CategoriaContacto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContactoUpdate {

    private long id;

    private String nombre;

    private String apellidos;

    private String telefono;

    private String correo;

    private String direccion;

     @NotNull(message = "La categoria no puede ser nula. Eliga entre Familia, Escuela, Amigo, Negocio, Novia, Novio, Ex o Amante")
    private CategoriaContacto categoria;
}
