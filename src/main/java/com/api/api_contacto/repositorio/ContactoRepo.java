package com.api.api_contacto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.api_contacto.modelo.Contacto;

@Repository
public interface ContactoRepo extends JpaRepository<Contacto,Long>{
}