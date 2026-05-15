package com.manuelz.bankingapi.dtos.request;

import com.manuelz.bankingapi.models.enums.Moneda;
import com.manuelz.bankingapi.models.enums.TipoCuenta;

public record AccountRequestDTO(Long userid, Moneda moneda, TipoCuenta tipoCuenta) {
}
