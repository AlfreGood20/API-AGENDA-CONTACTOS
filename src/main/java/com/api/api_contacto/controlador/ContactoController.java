package com.api.api_contacto.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.api_contacto.dtos.request.ContactoRequest;
import com.api.api_contacto.dtos.response.ContactoResponse;
import com.api.api_contacto.dtos.update.ContactoUpdate;
import com.api.api_contacto.servicio.ContactoServ;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
public class ContactoController {

    private final ContactoServ servicio;

    public ContactoController(ContactoServ servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/contacto")
    public ResponseEntity<ContactoResponse> nuevo(@Valid @RequestBody ContactoRequest contacto) {
        return new ResponseEntity<ContactoResponse>(servicio.crear(contacto), HttpStatus.CREATED);
    }

    @GetMapping("/contacto/{id}")
    public ResponseEntity<ContactoResponse> obtenerPorId(@PathVariable long id){
        return ResponseEntity.ok().body(servicio.buscarPorId(id));
    }

    @GetMapping("/contactos")
    public ResponseEntity<List<ContactoResponse>> listaContactosUsuario() {
        return ResponseEntity.ok().body(servicio.listaContactos());
    }

    @GetMapping("/contacto")
    public ResponseEntity<List<ContactoResponse>> obtenerPorNombreUsuario(@RequestParam String nombre) {
        return ResponseEntity.ok().body(servicio.buscarPorNombre(nombre));
    }

    @GetMapping("/contactos/favoritos")
    public ResponseEntity<List<ContactoResponse>> listaContactosFavoritosUsuario() {
        return ResponseEntity.ok().body(servicio.listaContactosFavoritos());
    }

    @GetMapping("/admin/contactos")
    public ResponseEntity<List<ContactoResponse>> listasContactosAdmin() {
        return ResponseEntity.ok().body(servicio.listaGlobal());
    }

    @PutMapping("/contacto/{id}")
    public ResponseEntity<ContactoResponse> actualizarContactoUsuario(@PathVariable long id,@Valid @RequestBody ContactoUpdate contactoUpdate) {
        return ResponseEntity.ok().body(servicio.actualizarContacto(id,contactoUpdate));
    }

    @DeleteMapping("/contacto/{id}")
    public ResponseEntity<Void> eliminarContactoPorId(@PathVariable long id){
        servicio.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/contacto/{id}")
    public ResponseEntity<ContactoResponse> cambiarEstadoFavoritoContactoUsuario(@PathVariable long id, @RequestParam boolean estado) {
        return ResponseEntity.ok().body(servicio.cambiarEstadoFavorito(estado, id));
    }
}