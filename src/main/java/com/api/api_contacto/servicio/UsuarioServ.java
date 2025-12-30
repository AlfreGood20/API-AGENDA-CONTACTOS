package com.api.api_contacto.servicio;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.api_contacto.dtos.request.UsuarioRequest;
import com.api.api_contacto.dtos.response.UsuarioResponse;
import com.api.api_contacto.exepciones.ExepcionRecursoNoEncontrado;
import com.api.api_contacto.mappers.UsuarioMapper;
import com.api.api_contacto.modelo.Rol;
import com.api.api_contacto.modelo.Usuario;
import com.api.api_contacto.repositorio.RolRepo;
import com.api.api_contacto.repositorio.UsuarioRepo;
import com.api.api_contacto.utils.IUsuarioServ;


@SuppressWarnings("null")
@Service
public class UsuarioServ implements IUsuarioServ{

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private UsuarioMapper mapper;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private RolRepo rolRepo;

    @Override
    public UsuarioResponse crear(UsuarioRequest request) {

        Set<Rol> roles = request.getRolIds()
            .stream()
            .map(rol -> rolRepo.findById(rol).orElseThrow(()-> new ExepcionRecursoNoEncontrado("Roles no encontrados")))
            .collect(Collectors.toSet());

        Usuario nuevo = mapper.toEntity(request, roles);
        nuevo.setContrasena(encoder.encode(nuevo.getContrasena()));

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