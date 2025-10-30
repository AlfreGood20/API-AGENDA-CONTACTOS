package com.api.api_contacto.utils;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Login {

    @Email
    private String correo;

    @Size(min = 8, message = "La contraseña tiene que ser mayor a 8 caracteres")
    private String contrasena;
}