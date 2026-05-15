package com.manuelz.bankingapi.dtos.response;

import com.manuelz.bankingapi.models.enums.TipoTransaccion;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransaccionResponseDTO(String aliasOrigen, String aliasDestino, BigDecimal monto, LocalDateTime fecha,
                                     LocalDateTime fechaCreacion) {
}
