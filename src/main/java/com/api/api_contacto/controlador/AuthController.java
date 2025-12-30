package com.api.api_contacto.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.api_contacto.dtos.request.LoginRequest;
import com.api.api_contacto.dtos.response.LoginResponse;
import com.api.api_contacto.servicio.AuthServ;
import com.api.api_contacto.utils.StatusCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(description = "Endpoint autenticación", name = "Autenticación")
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthServ authServ;

    @Operation(summary = "Aquí te loguearás para obtener el token de autenticación", description = "Obtendrás token de autenticación usando correo y contraseña, que posteriormente usarás")
    @ApiResponses({
        @ApiResponse(responseCode = StatusCode.OK, description = "Autenticación exitosa", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))),
        @ApiResponse(responseCode = "401", description = "Credenciales inválidas", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/auth")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authServ.autenticarse(request));
    }
}