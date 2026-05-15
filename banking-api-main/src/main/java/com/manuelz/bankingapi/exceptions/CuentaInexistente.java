package com.manuelz.bankingapi.exceptions;

public class CuentaInexistente extends RuntimeException{
    public CuentaInexistente(String mensaje){
        super(mensaje);
    }
}
