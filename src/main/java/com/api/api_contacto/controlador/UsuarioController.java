package com.api.api_contacto.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.api_contacto.dtos.request.UsuarioRequest;
import com.api.api_contacto.dtos.response.UsuarioResponse;
import com.api.api_contacto.servicio.UsuarioServ;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import com.api.api_contacto.exepciones.MensajeExepcion;
import com.api.api_contacto.utils.StatusCode;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Tag(name = "Usuario", description = "Aqui estaran los enpoint para el usuario")
@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final UsuarioServ servicio;

    public UsuarioController(UsuarioServ servicio) {
        this.servicio = servicio;
    }


    
    @Operation(summary = "Crear usuario", description = "Crea un nuevo usuario en el sistema")
    @ApiResponses({
        @ApiResponse(responseCode = StatusCode.CREATED, description = "Usuario creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))),
        @ApiResponse(responseCode = StatusCode.BAD_REQUEST, description = "Datos inválidos", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = StatusCode.UNAUTHORIZED, description = "No autorizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.INTERNAL_SERVER_ERROR, description = "Error del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.METHOD_NOT_ALLOWED, description = "Método no soportado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class)))
    })
    @PostMapping("/usuario")
    public ResponseEntity<UsuarioResponse> nuevo(@Valid @RequestBody UsuarioRequest usuario) {
        return new ResponseEntity<UsuarioResponse>(servicio.crear(usuario), HttpStatus.CREATED);
    }




    @Operation(summary = "Listar usuarios (admin)", description = "Devuelve el listado de usuarios (requiere permisos de administrador)")
    @ApiResponses({
        @ApiResponse(responseCode = StatusCode.OK, description = "Listado de usuarios", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class))),
        @ApiResponse(responseCode = StatusCode.FORBIDDEN, description = "Acceso denegado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.UNAUTHORIZED, description = "No autorizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.INTERNAL_SERVER_ERROR, description = "Error del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class)))
    })
    @GetMapping("/admin/usuarios")
    public ResponseEntity<List<UsuarioResponse>> obtenerListado() {
        return ResponseEntity.ok().body(servicio.listado());
    }



    @Operation(summary = "Eliminar usuario (admin)", description = "Elimina un usuario por su id (requiere permisos de administrador)")
    @ApiResponses({
        @ApiResponse(responseCode = StatusCode.NO_CONTENT, description = "Eliminado correctamente", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = StatusCode.NOT_FOUND, description = "Usuario no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.FORBIDDEN, description = "Acceso denegado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.UNAUTHORIZED, description = "No autorizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.INTERNAL_SERVER_ERROR, description = "Error del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class)))
    })
    @DeleteMapping("/admin/usuario/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable long id){
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}