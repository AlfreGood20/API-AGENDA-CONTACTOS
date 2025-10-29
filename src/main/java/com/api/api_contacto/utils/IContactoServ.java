package com.api.api_contacto.utils;

import java.util.List;

import com.api.api_contacto.dtos.request.ContactoRequest;
import com.api.api_contacto.dtos.response.ContactoResponse;

public interface IContactoServ {

    ContactoResponse crear(ContactoRequest request);
    List<ContactoResponse> listado();
    ContactoResponse obtenerPorId(long id);
    ContactoResponse obtenerPorNombre(String nombre);
    ContactoResponse actualizar(long id, ContactoRequest request);
    void eliminarPorId(long id);
}