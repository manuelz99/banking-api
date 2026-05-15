package com.manuelz.bankingapi.exceptions;

public class CuentaDuplicadaEx extends RuntimeException{
    public CuentaDuplicadaEx(String mensaje){
        super(mensaje);
    }
}
