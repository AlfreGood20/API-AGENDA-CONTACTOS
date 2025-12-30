package com.api.api_contacto.servicio;

import java.time.LocalDateTime;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.api.api_contacto.configuracion.security.JwtServ;
import com.api.api_contacto.configuracion.security.UsuarioDetails;
import com.api.api_contacto.dtos.request.LoginRequest;
import com.api.api_contacto.dtos.response.LoginResponse;
import com.api.api_contacto.repositorio.UsuarioRepo;

@Service
public class AuthServ {

    private final UsuarioRepo usuarioRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtServ jwtServ;

    public AuthServ(UsuarioRepo usuarioRepo, AuthenticationManager authenticationManager, JwtServ jwtServ) {
        this.usuarioRepo = usuarioRepo;
        this.authenticationManager = authenticationManager;
        this.jwtServ = jwtServ;
    }

    public LoginResponse autenticarse (LoginRequest request){

        if(!usuarioRepo.existsByCorreo(request.getCorreo())) throw new UsernameNotFoundException("Usuario no encontrado");

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getCorreo(),request.getContrasena())
        );

        String token = jwtServ.getToken((UsuarioDetails)authentication.getPrincipal());
        return new LoginResponse(LocalDateTime.now(), token);
    }
}