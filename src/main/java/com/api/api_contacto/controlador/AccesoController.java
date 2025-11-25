package com.api.api_contacto.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.api_contacto.dtos.response.UsuarioResponse;
import com.api.api_contacto.exepciones.MensajeExepcion;
import com.api.api_contacto.servicio.AccesoServ;
import com.api.api_contacto.utils.Login;
import com.api.api_contacto.utils.StatusCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@Tag(name = "Acceso", description = "Aqui estaran los enpoint para logiarse, deslogiarse y obtener el usuario logiado")
@RestController
@RequestMapping("/api")
public class AccesoController {

    private final AccesoServ accesoServ;
    public AccesoController(AccesoServ accesoServ) {
        this.accesoServ = accesoServ;
    }

    @Operation(summary = "Iniciar Sesión", description = "Logiarse usuario con su contraseña y correo.")

    @ApiResponses({
        @ApiResponse(responseCode = StatusCode.OK, description = "Iniciastes Session", content = @Content(mediaType = "application/json",schema = @Schema(implementation = UsuarioResponse.class))),
        @ApiResponse(responseCode = StatusCode.UNAUTHORIZED, description = "Acceso no autorizado por datos invalidos", content = @Content(mediaType = "application/json",schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.INTERNAL_SERVER_ERROR, description = "Error del servidor", content = @Content(mediaType = "application/json",schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.METHOD_NOT_ALLOWED, description="Metodo no soportado", content = @Content(mediaType = "application/json",schema = @Schema(implementation = MensajeExepcion.class)))
    })
    @PostMapping("/auth")
    public ResponseEntity<UsuarioResponse> darAcceso(@Valid @RequestBody Login crendenciales, HttpServletRequest httpRequest) {
        return ResponseEntity.ok().body(accesoServ.autenticar(crendenciales, httpRequest));
    }

    @Operation(summary = "Cerrar Sesión",description = "Deslogiarse usuario loguiado.")
    @GetMapping("/deauthenticate")
    public ResponseEntity<Void> cerrarSession(HttpSession session) {
        accesoServ.desAutenticarse(session);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener Usuario loguiado", description = "Obtienes informacion del usuario sus datos. En caso que no este loguiado se mandara un 401.")
    @ApiResponses({
        @ApiResponse(responseCode = StatusCode.OK, description = "Usuario logiado", content = @Content(mediaType = "application/json",schema = @Schema(implementation = UsuarioResponse.class))),
        @ApiResponse(responseCode = StatusCode.UNAUTHORIZED, description = "Acceso no autorizado por datos invalidos", content = @Content(mediaType = "application/json",schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.INTERNAL_SERVER_ERROR, description = "Error del servidor", content = @Content(mediaType = "application/json",schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.METHOD_NOT_ALLOWED, description="Metodo no soportado", content = @Content(mediaType = "application/json",schema = @Schema(implementation = MensajeExepcion.class)))
    })
    @GetMapping("/perfil")
    public ResponseEntity<UsuarioResponse> perfilLogiado(HttpSession session) {
        return ResponseEntity.ok().body(accesoServ.obtenerPerfilLogiado(session));
    }  
}