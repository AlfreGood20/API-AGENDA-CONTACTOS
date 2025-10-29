package com.api.api_contacto.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.api.api_contacto.dtos.request.UsuarioRequest;
import com.api.api_contacto.dtos.response.UsuarioResponse;
import com.api.api_contacto.modelo.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {


    @Mapping(target = "contactos", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "id", ignore = true)
    Usuario toEntity(UsuarioRequest request);

    UsuarioResponse toDto(Usuario response);

    List<UsuarioResponse> listToDto(List<Usuario> listado);
}