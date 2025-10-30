package com.api.api_contacto.exepciones;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExepciones {

    @ExceptionHandler(ExepcionRecursoNoEncontrado.class)
    public ResponseEntity<?> manejarRecursosNoEncontrado(ExepcionRecursoNoEncontrado ex, HttpServletRequest request){
        MensajeExepcion mensaje= MensajeExepcion.builder()
            .timestamp(LocalDateTime.now())
            .status(404)
            .error("Recurso no encontrado")
            .mensaje(ex.getMessage())
            .ruta(request.getRequestURI()).build();

        return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler()
    public ResponseEntity<?> manejarErrorInesperado(Exception ex, HttpServletRequest request){
        MensajeExepcion mensaje= MensajeExepcion.builder()
            .timestamp(LocalDateTime.now())
            .status(500)
            .error("Algo Salio mal")
            .mensaje(ex.getMessage())
            .ruta(request.getRequestURI()).build();
        
        return ResponseEntity.internalServerError().body(mensaje);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> manejarEndpointNoExistente(NoHandlerFoundException ex, HttpServletRequest request) {
            MensajeExepcion respuesta = MensajeExepcion.builder()
                .timestamp(LocalDateTime.now())
                .status(404)
                .error("Endpoint no existente")
                .mensaje(request.getMethod()+" "+request.getRequestURI()+" no existe")
                .ruta(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> manejarMetodoNoSoportado(HttpServletRequest request){
        MensajeExepcion repuesta= MensajeExepcion.builder()
            .timestamp(LocalDateTime.now())
            .status(405)
            .error("Metodo No Soportado")
            .mensaje("Este enpoint no soporta dicho metodo")
            .ruta(request.getRequestURI())
            .build();

        return new ResponseEntity<>(repuesta,HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> cuerpoInvalido(HttpMessageNotReadableException ex, HttpServletRequest request) {
        MensajeExepcion respuesta = MensajeExepcion.builder()
                .timestamp(LocalDateTime.now())
                .status(400)
                .error("Cuerpo de la solicitud invalida")
                .mensaje("El cuerpo de la solicitud está vacío o mal formado.")
                .ruta(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(respuesta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> manejarValidaciones(MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> mensaje = new HashMap<>();
        ex.getFieldErrors().stream().forEach(error -> mensaje.put(error.getField(), error.getDefaultMessage()));

        MensajeExepcion respuesta = MensajeExepcion.builder()
                .timestamp(LocalDateTime.now())
                .status(400)
                .error("Datos invalidos")
                .mensaje(mensaje.toString())
                .ruta(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(respuesta);
    }

    @ExceptionHandler(ExepcionAutenticacionRechazada.class)
    public ResponseEntity<?> manejarAutenticacionFallida(ExepcionAutenticacionRechazada ex, HttpServletRequest request){
        MensajeExepcion mensaje = MensajeExepcion.builder()
            .timestamp(LocalDateTime.now())
            .status(401)
            .error("Autenticación fallida")
            .mensaje(ex.getMessage())
            .ruta(request.getRequestURI())
            .build();

        return new ResponseEntity<>(mensaje,HttpStatus.UNAUTHORIZED);
    }
}