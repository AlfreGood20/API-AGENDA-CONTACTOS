package com.api.api_contacto.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.api_contacto.dtos.request.ContactoRequest;
import com.api.api_contacto.dtos.response.ContactoResponse;
import com.api.api_contacto.dtos.response.UsuarioResponse;
import com.api.api_contacto.dtos.update.ContactoUpdate;
import com.api.api_contacto.exepciones.MensajeExepcion;
import com.api.api_contacto.servicio.ContactoServ;
import com.api.api_contacto.utils.StatusCode;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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


@Tag(name = "Contacto", description = "Aqui estaran los enpoint acciones del usuario")
@RestController
@RequestMapping("/api")
public class ContactoController {

    private final ContactoServ servicio;

    public ContactoController(ContactoServ servicio) {
        this.servicio = servicio;
    }


    @Operation(summary = "Crear un nuevo contacto", description = "Creas un nuevo contacto")

    @ApiResponses({
        @ApiResponse(responseCode = StatusCode.CREATED, description = "Se creo correctamente el contacto", content = @Content(mediaType = "application/json",schema = @Schema(implementation = UsuarioResponse.class))),
        @ApiResponse(responseCode = StatusCode.BAD_REQUEST,description = "Datos invalidos y/o cuerpo mas formado",content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = StatusCode.UNAUTHORIZED, description = "No estas logiado", content = @Content(mediaType = "application/json",schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.INTERNAL_SERVER_ERROR, description = "Error del servidor", content = @Content(mediaType = "application/json",schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.METHOD_NOT_ALLOWED, description="Metodo no soportado", content = @Content(mediaType = "application/json",schema = @Schema(implementation = MensajeExepcion.class)))
    })
    @PostMapping("/contacto")
    public ResponseEntity<ContactoResponse> nuevo(@Valid @RequestBody ContactoRequest contacto) {
        return new ResponseEntity<ContactoResponse>(servicio.crear(contacto), HttpStatus.CREATED);
    }

    

    @Operation(summary = "Obtener contacto por id", description = "Devuelve un contacto por su identificador")
    @ApiResponses({
        @ApiResponse(responseCode = StatusCode.OK, description = "Contacto encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContactoResponse.class))),
        @ApiResponse(responseCode = StatusCode.NOT_FOUND, description = "Contacto no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.UNAUTHORIZED, description = "No autorizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.INTERNAL_SERVER_ERROR, description = "Error del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.METHOD_NOT_ALLOWED, description = "Metodo no soportado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class)))
    })
    @GetMapping("/contacto/{id}")
    public ResponseEntity<ContactoResponse> obtenerPorId(@PathVariable long id){
        return ResponseEntity.ok().body(servicio.buscarPorId(id));
    }



    @Operation(summary = "Listar contactos del usuario", description = "Devuelve la lista de contactos del usuario logueado")
    @ApiResponses({
        @ApiResponse(responseCode = StatusCode.OK, description = "Lista de contactos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContactoResponse.class))),
        @ApiResponse(responseCode = StatusCode.UNAUTHORIZED, description = "No autorizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.INTERNAL_SERVER_ERROR, description = "Error del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class)))
    })
    @GetMapping("/contactos")
    public ResponseEntity<List<ContactoResponse>> listaContactosUsuario() {
        return ResponseEntity.ok().body(servicio.listaContactos());
    }



    @Operation(summary = "Buscar contactos por nombre", description = "Busca contactos del usuario por nombre (query param 'nombre')")
    @ApiResponses({
        @ApiResponse(responseCode = StatusCode.OK, description = "Lista filtrada por nombre", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContactoResponse.class))),
        @ApiResponse(responseCode = StatusCode.BAD_REQUEST, description = "Parametro 'nombre' inválido", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = StatusCode.UNAUTHORIZED, description = "No autorizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.INTERNAL_SERVER_ERROR, description = "Error del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class)))
    })
    @GetMapping("/contacto")
    public ResponseEntity<List<ContactoResponse>> obtenerPorNombreUsuario(@RequestParam String nombre) {
        return ResponseEntity.ok().body(servicio.buscarPorNombre(nombre));
    }



    @Operation(summary = "Listar contactos favoritos", description = "Devuelve los contactos marcados como favoritos del usuario")
    @ApiResponses({
        @ApiResponse(responseCode = StatusCode.OK, description = "Lista de favoritos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContactoResponse.class))),
        @ApiResponse(responseCode = StatusCode.UNAUTHORIZED, description = "No autorizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.INTERNAL_SERVER_ERROR, description = "Error del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class)))
    })
    @GetMapping("/contactos/favoritos")
    public ResponseEntity<List<ContactoResponse>> listaContactosFavoritosUsuario() {
        return ResponseEntity.ok().body(servicio.listaContactosFavoritos());
    }



    @Operation(summary = "Listar contactos (admin)", description = "Listado global de contactos (requiere permisos de administrador)")
    @ApiResponses({
        @ApiResponse(responseCode = StatusCode.OK, description = "Lista global de contactos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContactoResponse.class))),
        @ApiResponse(responseCode = StatusCode.FORBIDDEN, description = "Acceso denegado (solo admin)", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.UNAUTHORIZED, description = "No autorizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.INTERNAL_SERVER_ERROR, description = "Error del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class)))
    })
    @GetMapping("/admin/contactos")
    public ResponseEntity<List<ContactoResponse>> listasContactosAdmin() {
        return ResponseEntity.ok().body(servicio.listaGlobal());
    }



    @Operation(summary = "Actualizar contacto", description = "Actualiza los datos de un contacto por id")
    @ApiResponses({
        @ApiResponse(responseCode = StatusCode.OK, description = "Contacto actualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContactoResponse.class))),
        @ApiResponse(responseCode = StatusCode.BAD_REQUEST, description = "Datos inválidos", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = StatusCode.NOT_FOUND, description = "Contacto no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.UNAUTHORIZED, description = "No autorizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.INTERNAL_SERVER_ERROR, description = "Error del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class)))
    })
    @PutMapping("/contacto/{id}")
    public ResponseEntity<ContactoResponse> actualizarContactoUsuario(@PathVariable long id,@Valid @RequestBody ContactoUpdate contactoUpdate) {
        return ResponseEntity.ok().body(servicio.actualizarContacto(id,contactoUpdate));
    }



    @Operation(summary = "Eliminar contacto", description = "Elimina un contacto por su id")
    @ApiResponses({
        @ApiResponse(responseCode = StatusCode.NO_CONTENT, description = "Eliminado correctamente", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = StatusCode.NOT_FOUND, description = "Contacto no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.UNAUTHORIZED, description = "No autorizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.INTERNAL_SERVER_ERROR, description = "Error del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class)))
    })
    @DeleteMapping("/contacto/{id}")
    public ResponseEntity<Void> eliminarContactoPorId(@PathVariable long id){
        servicio.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }



    @Operation(summary = "Cambiar estado favorito", description = "Activa o desactiva el estado favorito de un contacto")
    @ApiResponses({
        @ApiResponse(responseCode = StatusCode.OK, description = "Estado actualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContactoResponse.class))),
        @ApiResponse(responseCode = StatusCode.BAD_REQUEST, description = "Parametro 'estado' inválido", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = StatusCode.NOT_FOUND, description = "Contacto no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.UNAUTHORIZED, description = "No autorizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class))),
        @ApiResponse(responseCode = StatusCode.INTERNAL_SERVER_ERROR, description = "Error del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MensajeExepcion.class)))
    })
    @PatchMapping("/contacto/{id}")
    public ResponseEntity<ContactoResponse> cambiarEstadoFavoritoContactoUsuario(@PathVariable long id, @RequestParam boolean estado) {
        return ResponseEntity.ok().body(servicio.cambiarEstadoFavorito(estado, id));
    }
}