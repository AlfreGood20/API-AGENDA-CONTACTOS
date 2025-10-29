package com.api.api_contacto.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioRequest {

    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Apellidos es obligatorio")
    private String apellidos;

    @Size(min = 8, max = 10,message = "El numero no puede ser mayor a 10 digitos")
    private String telefono;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Correo invalido")
    private String correo;

    @NotBlank(message = "La contraseña es obligatorio")
    @Size(min = 8, max = 100, message = "La contraseña tiene que ser mayor a 8 caracteres")
    private String contrasena;
}