package com.api.api_contacto.servicio;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.api.api_contacto.dtos.request.ContactoRequest;
import com.api.api_contacto.dtos.response.ContactoResponse;
import com.api.api_contacto.dtos.response.UsuarioResponse;
import com.api.api_contacto.dtos.update.ContactoUpdate;
import com.api.api_contacto.exepciones.ExepcionRecursoNoEncontrado;
import com.api.api_contacto.exepciones.ExepcionAutenticacionRechazada;
import com.api.api_contacto.mappers.ContactoMapper;
import com.api.api_contacto.modelo.Contacto;
import com.api.api_contacto.modelo.Usuario;
import com.api.api_contacto.repositorio.ContactoRepo;
import com.api.api_contacto.repositorio.UsuarioRepo;
import com.api.api_contacto.utils.IContactoServ;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class ContactoServ implements IContactoServ {

    private final ContactoRepo contactoRepo;
    private final ContactoMapper mapper;
    private final UsuarioRepo usuarioRepo;

    public ContactoServ(ContactoRepo contactoRepo, ContactoMapper mapper, UsuarioRepo usuarioRepo) {
        this.contactoRepo = contactoRepo;
        this.mapper = mapper;
        this.usuarioRepo = usuarioRepo;
    }

    //OBTENER SESSION ACTIVA
    private HttpSession obtenerSesion() {
        ServletRequestAttributes atributos = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (atributos == null) {
            throw new ExepcionAutenticacionRechazada("No hay sesión activa");
        }
        return atributos.getRequest().getSession();
    }

    //OBTENER USUARIO ACTIVO
    private UsuarioResponse obtenerUsuarioDeSesion() {
        HttpSession session = obtenerSesion();
        Object atributo = session.getAttribute("usuario");
        if (atributo == null) {
            throw new ExepcionAutenticacionRechazada("No estás autenticado");
        }
        return (UsuarioResponse) atributo;
    }

    @Override
    public ContactoResponse crear(ContactoRequest request) {
        UsuarioResponse usuarioSesion = obtenerUsuarioDeSesion();
        
        Usuario usuario = usuarioRepo.findById(usuarioSesion.getId())
            .orElseThrow(() -> new ExepcionRecursoNoEncontrado("Usuario no encontrado"));

        Contacto nuevo = mapper.toEntity(request, usuario);
        return mapper.toDto(contactoRepo.save(nuevo));
    }

    @Override
    public List<ContactoResponse> listaContactos() {
        UsuarioResponse usuarioSesion = obtenerUsuarioDeSesion();
        return mapper.listToDto(contactoRepo.findByUsuarioId(usuarioSesion.getId()));
    }

    @Override
    public List<ContactoResponse> buscarPorNombre(String nombre) {
        UsuarioResponse usuarioSesion = obtenerUsuarioDeSesion();
        
        List<Contacto> contactos = contactoRepo.findByNombreAndUsuarioId(nombre, usuarioSesion.getId());
        if (contactos.isEmpty()) {
            throw new ExepcionRecursoNoEncontrado("Contacto con nombre '" + nombre + "' no encontrado");
        }

        return mapper.listToDto(contactos);
    }

    @Override
    public List<ContactoResponse> listaContactosFavoritos() {
        UsuarioResponse usuarioSesion = obtenerUsuarioDeSesion();
        return mapper.listToDto(contactoRepo.findByUsuarioIdAndFavoritoTrue(usuarioSesion.getId()));
    }

    @Override
    public List<ContactoResponse> listaGlobal() {
        return mapper.listToDto(contactoRepo.findAll());
    }

	@Override
	public void eliminarPorId(long id) {
        obtenerUsuarioDeSesion();

        Contacto eliminar = contactoRepo.findById(id)
            .orElseThrow(()-> new ExepcionRecursoNoEncontrado("Contacto no encontrado"));

        contactoRepo.delete(eliminar);
	}

    
    @Override
    @Transactional
    public ContactoResponse actualizarContacto(ContactoUpdate contactoUpdate){
        obtenerUsuarioDeSesion();

        Contacto actualizar = contactoRepo.findById(contactoUpdate.getId())
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
        obtenerUsuarioDeSesion();

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