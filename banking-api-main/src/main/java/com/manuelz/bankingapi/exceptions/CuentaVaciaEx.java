package com.manuelz.bankingapi.exceptions;

public class CuentaVaciaEx extends RuntimeException{
    public CuentaVaciaEx(String mensaje){
        super(mensaje);
    }
}
