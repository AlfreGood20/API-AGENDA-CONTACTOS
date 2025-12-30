package com.api.api_contacto.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequest {

    @Email(message = "Correo invalido incompleto")
    private String correo;

    @Size(min = 8, message = "La contraseña tiene que ser mayor a 8 caracteres")
    private String contrasena;
}