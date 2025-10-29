package com.api.api_contacto.servicio;

import java.util.List;

import com.api.api_contacto.dtos.request.ContactoRequest;
import com.api.api_contacto.dtos.response.ContactoResponse;
import com.api.api_contacto.repositorio.ContactoRepo;
import com.api.api_contacto.utils.IContactoServ;

public class ContactoServ implements IContactoServ{

    private final ContactoRepo contactoRepo;

    public ContactoServ(ContactoRepo contactoRepo) {
        this.contactoRepo = contactoRepo;
    }

    @Override
    public ContactoResponse crear(ContactoRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crear'");
    }

    @Override
    public List<ContactoResponse> listado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listado'");
    }

    @Override
    public ContactoResponse obtenerPorId(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPorId'");
    }

    @Override
    public ContactoResponse obtenerPorNombre(String nombre) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPorNombre'");
    }

    @Override
    public ContactoResponse actualizar(long id, ContactoRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    public void eliminarPorId(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarPorId'");
    }
}