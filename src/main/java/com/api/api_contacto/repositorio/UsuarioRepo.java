package com.api.api_contacto.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.api_contacto.modelo.Usuario;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByCorreo(String contrasena);
}