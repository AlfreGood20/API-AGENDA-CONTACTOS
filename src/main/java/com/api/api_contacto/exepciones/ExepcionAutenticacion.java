package com.api.api_contacto.exepciones;

import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExepcionAutenticacion implements AuthenticationEntryPoint{

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        MensajeExepcion body = MensajeExepcion.builder()
            .timestamp(LocalDateTime.now())
            .status(401)
            .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
            .mensaje("Credenciales invalidas")
            .ruta(request.getRequestURI())
            .build();

        if (request.getAttribute("JWT_ERROR") != null) {
            String attribute = (String) request.getAttribute("JWT_ERROR");
            String mensaje = (
                            attribute.equals("Empty") ? "Token vacio, ingrese token valido" : 
                            attribute.equals("expired") ? "Token expirado, vuelve autenticarte":
                            attribute.equals("malformed") ? "Token malformado, ingrese token valido":
                            attribute.equals("invalid-signature") ? "La firma del token no coinciden":
                            "Credenciales invalidas");

            body.setMensaje(mensaje);
        }

        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().write(objectMapper.writeValueAsString(body));
    }

}
