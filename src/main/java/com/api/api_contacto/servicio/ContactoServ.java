package com.api.api_contacto.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.api.api_contacto.configuracion.security.UsuarioDetails;
import com.api.api_contacto.dtos.request.ContactoRequest;
import com.api.api_contacto.dtos.response.ContactoResponse;
import com.api.api_contacto.dtos.update.ContactoUpdate;
import com.api.api_contacto.exepciones.ExepcionRecursoNoEncontrado;
import com.api.api_contacto.mappers.ContactoMapper;
import com.api.api_contacto.modelo.Contacto;
import com.api.api_contacto.modelo.Usuario;
import com.api.api_contacto.repositorio.ContactoRepo;
import com.api.api_contacto.repositorio.UsuarioRepo;
import com.api.api_contacto.utils.IContactoServ;
import jakarta.transaction.Transactional;

@Service
@SuppressWarnings("null")
public class ContactoServ implements IContactoServ {

    @Autowired
    private ContactoRepo contactoRepo;

    @Autowired
    private ContactoMapper mapper;

    @Autowired
    private UsuarioRepo usuarioRepo;


    private UsuarioDetails getUsuario(){
        return (UsuarioDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
    }


    @Override
    public ContactoResponse crear(ContactoRequest request) {

        Usuario usuario = usuarioRepo.findById(getUsuario().getId())
            .orElseThrow(() -> new ExepcionRecursoNoEncontrado("Usuario no encontrado"));
            
        Contacto nuevo = mapper.toEntity(request, usuario);
        return mapper.toDto(contactoRepo.save(nuevo));
    }

    @Override
    public List<ContactoResponse> listaContactos() {
        return mapper.listToDto(contactoRepo.findByUsuarioId(getUsuario().getId()));
    }

    @Override
    public List<ContactoResponse> buscarPorNombre(String nombre) {
        
        List<Contacto> contactos = contactoRepo.findByNombreAndUsuarioId(nombre, getUsuario().getId());
        if (contactos.isEmpty()) {
            throw new ExepcionRecursoNoEncontrado("Contacto con nombre '" + nombre + "' no encontrado");
        }

        return mapper.listToDto(contactos);
    }

    @Override
    public List<ContactoResponse> listaContactosFavoritos() {
        return mapper.listToDto(contactoRepo.findByUsuarioIdAndFavoritoTrue(getUsuario().getId()));
    }

    @Override
    public List<ContactoResponse> listaGlobal() {
        return mapper.listToDto(contactoRepo.findAll());
    }

    @Override
	public void eliminarPorId(long id) {
        Contacto eliminar = contactoRepo.findById(id)
            .orElseThrow(()-> new ExepcionRecursoNoEncontrado("Contacto no encontrado"));

        contactoRepo.delete(eliminar);
	}

    
    @Override
    @Transactional
    public ContactoResponse actualizarContacto(long id,ContactoUpdate contactoUpdate){
        Contacto actualizar = contactoRepo.findById(id)
            .orElseThrow(()-> new ExepcionRecursoNoEncontrado("Contacto no encontrado"));

        actualizar.setNombre(contactoUpdate.getNombre());
        actualizar.setApellidos(contactoUpdate.getApellidos());
        actualizar.setTelefono(contactoUpdate.getTelefono());
        actualizar.setCorreo(contactoUpdate.getCorreo());
        actualizar.setDireccion(contactoUpdate.getDireccion());
        actualizar.setCategoria(contactoUpdate.getCategoria());

        return mapper.toDto(contactoRepo.save(actualizar));
    }

    @Override
    @Transactional
    public ContactoResponse cambiarEstadoFavorito(boolean estado, long id) {
        Contacto actualizar = contactoRepo.findById(id)
            .orElseThrow(()-> new ExepcionRecursoNoEncontrado("Contacto no encontrado"));

        actualizar.setFavorito(estado);

        return mapper.toDto(contactoRepo.save(actualizar));
    }

    @Override
    public ContactoResponse buscarPorId(long id) {
        return mapper.toDto(contactoRepo.findById(id).orElseThrow(()-> new ExepcionRecursoNoEncontrado("Contacto no encontrado")));
    }
}