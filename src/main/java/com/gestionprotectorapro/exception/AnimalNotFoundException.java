package com.gestionprotectorapro.exception;

public class AnimalNotFoundException extends RuntimeException{
    public AnimalNotFoundException(String mensaje) {
        super(mensaje);
    }
}
