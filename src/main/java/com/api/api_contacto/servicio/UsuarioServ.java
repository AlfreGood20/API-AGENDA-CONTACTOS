package com.api.api_contacto.servicio;

import java.util.List;
import com.api.api_contacto.dtos.request.UsuarioRequest;
import com.api.api_contacto.dtos.response.UsuarioResponse;
import com.api.api_contacto.exepciones.ExepcionRecursoNoEncontrado;
import com.api.api_contacto.mappers.UsuarioMapper;
import com.api.api_contacto.modelo.Usuario;
import com.api.api_contacto.repositorio.UsuarioRepo;
import com.api.api_contacto.utils.IUsuarioServ;

public class UsuarioServ implements IUsuarioServ{

    private final UsuarioRepo usuarioRepo;
    private final UsuarioMapper mapper;

    public UsuarioServ(UsuarioRepo usuarioRepo, UsuarioMapper mapper) {
        this.usuarioRepo = usuarioRepo;
        this.mapper=mapper;
    }

    @Override
    public UsuarioResponse crear(UsuarioRequest request) {
        Usuario nuevo = mapper.toEntity(request);
        return mapper.toDto(usuarioRepo.save(nuevo));
    }

    @Override
    public List<UsuarioResponse> listado() {
        return mapper.listToDto(usuarioRepo.findAll());
    }

    @Override
    public void eliminar(long id) {
        Usuario eliminar = usuarioRepo.findById(id).orElseThrow(()-> new ExepcionRecursoNoEncontrado("Usuario id "+id+" no encontrado"));
        usuarioRepo.delete(eliminar);
    }
}