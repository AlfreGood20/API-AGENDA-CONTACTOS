package com.api.api_contacto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.api_contacto.modelo.Rol;

public interface RolRepo extends JpaRepository<Rol,Long>{
}