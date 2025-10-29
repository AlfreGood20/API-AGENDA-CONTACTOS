package com.api.api_contacto.exepciones;

public class ExepcionRecursoNoEncontrado extends RuntimeException{

    public ExepcionRecursoNoEncontrado(String mensaje){
        super(mensaje);
    }
}