package com.api.api_contacto.utils;

import java.util.List;

import com.api.api_contacto.dtos.request.UsuarioRequest;
import com.api.api_contacto.dtos.response.UsuarioResponse;

public interface IUsuarioServ {

    UsuarioResponse crear(UsuarioRequest request);
    List<UsuarioResponse> listado();
    void eliminar(long id);
}