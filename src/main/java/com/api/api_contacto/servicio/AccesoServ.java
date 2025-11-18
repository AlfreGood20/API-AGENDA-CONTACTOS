package com.api.api_contacto.servicio;

import org.springframework.stereotype.Service;
import com.api.api_contacto.dtos.response.UsuarioResponse;
import com.api.api_contacto.exepciones.ExepcionAutenticacionRechazada;
import com.api.api_contacto.mappers.UsuarioMapper;
import com.api.api_contacto.modelo.Usuario;
import com.api.api_contacto.repositorio.UsuarioRepo;
import com.api.api_contacto.utils.Login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class AccesoServ {

    private final UsuarioRepo usuarioRepo;
    private final UsuarioMapper usuarioMapper;

    public AccesoServ(UsuarioRepo usuarioRepo, UsuarioMapper usuarioMapper) {
        this.usuarioRepo = usuarioRepo;
        this.usuarioMapper=usuarioMapper;
    }

    

    public UsuarioResponse autenticar(Login request,  HttpServletRequest httpRequest){
        Usuario usuario = usuarioRepo.findByCorreo(request.getCorreo())
        .orElseThrow(() -> new ExepcionAutenticacionRechazada("No existe usuario con correo "+request.getCorreo()));

        if(!usuario.getContrasena().equals(request.getContrasena())) throw new ExepcionAutenticacionRechazada("Contraseña incorrecta");

        UsuarioResponse usuarioDto = usuarioMapper.toDto(usuario);
        
        HttpSession oldSession = httpRequest.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
        HttpSession newSession = httpRequest.getSession(true);
        newSession.setAttribute("usuario", usuarioDto);
        return usuarioDto;
    }


    public void desAutenticarse(HttpSession session){
        session.invalidate();
    }


    
    public UsuarioResponse obtenerPerfilLogiado(HttpSession session){
        Object atributo = session.getAttribute("usuario");
        if(atributo == null)  throw new ExepcionAutenticacionRechazada("No estás autenticado");

        return (UsuarioResponse) atributo;
    }
}