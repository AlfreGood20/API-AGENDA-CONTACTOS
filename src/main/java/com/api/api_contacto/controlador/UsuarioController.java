package com.api.api_contacto.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.api_contacto.dtos.request.UsuarioRequest;
import com.api.api_contacto.dtos.response.UsuarioResponse;
import com.api.api_contacto.servicio.UsuarioServ;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final UsuarioServ servicio;

    public UsuarioController(UsuarioServ servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioResponse> nuevo(@Valid @RequestBody UsuarioRequest usuario) {
        return new ResponseEntity<UsuarioResponse>(servicio.crear(usuario), HttpStatus.CREATED);
    }

    @GetMapping("/admin/usuarios")
    public ResponseEntity<List<UsuarioResponse>> obtenerListado() {
        return ResponseEntity.ok().body(servicio.listado());
    }

    @DeleteMapping("/admin/usuario/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable long id){
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}