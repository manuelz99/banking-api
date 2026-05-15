package com.manuelz.bankingapi.dtos.response;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public class ErrorResponse {
    String mensaje;
    LocalDateTime fecha;
}
