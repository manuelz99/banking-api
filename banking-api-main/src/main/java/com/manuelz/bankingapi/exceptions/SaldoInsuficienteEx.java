package com.manuelz.bankingapi.exceptions;

public class SaldoInsuficienteEx extends RuntimeException{
    public SaldoInsuficienteEx(String mensaje){
        super(mensaje);
    }
}
