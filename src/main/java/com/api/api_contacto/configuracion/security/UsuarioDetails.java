package com.api.api_contacto.configuracion.security;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.api.api_contacto.modelo.Usuario;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class UsuarioDetails implements UserDetails{

    private Usuario usuario;

    public long getId(){
        return usuario.getId();
    }

    public String getNombre(){
        return usuario.getNombre();
    }

    public String getApellidos(){
        return usuario.getApellidos();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuario.getRoles()
            .stream()
            .map(rol -> new SimpleGrantedAuthority("ROLE_"+rol.getNombre().toString()))
            .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return usuario.getContrasena();
    }

    @Override
    public String getUsername() {
        return usuario.getCorreo();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}