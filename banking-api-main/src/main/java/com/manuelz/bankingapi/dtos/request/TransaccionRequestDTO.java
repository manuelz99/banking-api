package com.manuelz.bankingapi.dtos.request;

import java.math.BigDecimal;

public record TransaccionRequestDTO(String aliasOrigen, String aliasDestino, BigDecimal monto) {
}
