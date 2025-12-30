package com.api.api_contacto.configuracion.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.api.api_contacto.modelo.Usuario;
import com.api.api_contacto.repositorio.UsuarioRepo;

@Service
public class UsuarioDetailsServ implements UserDetailsService{

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByCorreo(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario "+username+" no encontrado"));

        return new UsuarioDetails(usuario);
    }

}