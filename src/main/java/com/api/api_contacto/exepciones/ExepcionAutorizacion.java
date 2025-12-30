package com.api.api_contacto.exepciones;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class ExepcionAutorizacion implements AccessDeniedHandler{

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        MensajeExepcion body = MensajeExepcion.builder()
            .timestamp(LocalDateTime.now())
            .status(403)
            .error(HttpStatus.FORBIDDEN.getReasonPhrase())
            .mensaje("Sin permiso a este recurso")
            .ruta(request.getRequestURI())
            .build();

        response.setContentType("application/json");
        response.setStatus(403);
        response.getWriter().write(objectMapper.writeValueAsString(body));
    }
}