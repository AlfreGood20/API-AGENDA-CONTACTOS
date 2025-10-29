package com.api.api_contacto.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.api.api_contacto.dtos.request.ContactoRequest;
import com.api.api_contacto.dtos.response.ContactoResponse;
import com.api.api_contacto.modelo.Contacto;
import com.api.api_contacto.modelo.Usuario;

@Mapper(componentModel = "spring")
public interface ContactoMapper {

    @Mapping(target = "favorito", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", source = "usuarioObt")
    @Mapping(target = "telefono", source = "request.telefono")
    @Mapping(target = "apellidos", source = "request.apellidos")
    @Mapping(target = "correo", source = "request.correo")
    @Mapping(target = "nombre", source = "request.nombre")
    Contacto toEntity(ContactoRequest request, Usuario usuarioObt);

    ContactoResponse toDto(Contacto response);

    List<ContactoResponse> listToDto(List<Contacto> contactos);
}