package com.api.api_contacto.exepciones;

public class ExepcionAutenticacionRechazada extends RuntimeException{
    
    public ExepcionAutenticacionRechazada(String mensaje){
        super(mensaje);
    }
}
