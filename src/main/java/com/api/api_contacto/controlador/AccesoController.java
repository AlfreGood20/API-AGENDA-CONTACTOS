package com.api.api_contacto.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.api_contacto.dtos.response.UsuarioResponse;
import com.api.api_contacto.servicio.AccesoServ;
import com.api.api_contacto.utils.Login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
public class AccesoController {

    private final AccesoServ accesoServ;

    

    public AccesoController(AccesoServ accesoServ) {
        this.accesoServ = accesoServ;
    }


    @PostMapping("/auth")
    public ResponseEntity<UsuarioResponse> darAcceso(@Valid @RequestBody Login crendenciales, HttpServletRequest httpRequest) {
        return ResponseEntity.ok().body(accesoServ.autenticar(crendenciales, httpRequest));
    }


    @GetMapping("/deauthenticate")
    public ResponseEntity<Void> cerrarSession(HttpSession session) {
        accesoServ.desAutenticarse(session);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/perfil")
    public ResponseEntity<UsuarioResponse> perfilLogiado(HttpSession session) {
        return ResponseEntity.ok().body(accesoServ.obtenerPerfilLogiado(session));
    }
    
}