package com.api.api_contacto.mappers;

import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.api.api_contacto.dtos.request.UsuarioRequest;
import com.api.api_contacto.dtos.response.UsuarioResponse;
import com.api.api_contacto.modelo.Rol;
import com.api.api_contacto.modelo.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {


    @Mapping(target = "contactos", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", source = "roles")
    Usuario toEntity(UsuarioRequest request, Set<Rol> roles);

    UsuarioResponse toDto(Usuario response);

    List<UsuarioResponse> listToDto(List<Usuario> listado);
}