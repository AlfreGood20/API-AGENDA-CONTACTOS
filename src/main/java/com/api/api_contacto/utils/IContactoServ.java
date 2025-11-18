package com.api.api_contacto.utils;

import java.util.List;
import com.api.api_contacto.dtos.request.ContactoRequest;
import com.api.api_contacto.dtos.response.ContactoResponse;
import com.api.api_contacto.dtos.update.ContactoUpdate;

public interface IContactoServ {

    ContactoResponse crear(ContactoRequest request);
    List<ContactoResponse> listaContactos();
    List<ContactoResponse> buscarPorNombre(String nombre);
    List<ContactoResponse> listaContactosFavoritos();
    void eliminarPorId(long id);
    ContactoResponse actualizarContacto(long id,ContactoUpdate contactoUpdate);  
    ContactoResponse cambiarEstadoFavorito(boolean estado, long id);  
    List<ContactoResponse> listaGlobal();
    ContactoResponse buscarPorId(long id);
}