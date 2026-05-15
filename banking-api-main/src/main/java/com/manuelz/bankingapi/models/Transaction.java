package com.manuelz.bankingapi.models;

import com.manuelz.bankingapi.models.enums.TipoTransaccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacciones")
 @Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 @Column(nullable = false)
 private String aliasOrigen;
 @Column (nullable = false)
 private String aliasDestino;
 @Column (nullable = false)
 private BigDecimal monto;
 @Enumerated(EnumType.STRING)
 @Column (nullable = false)
 private TipoTransaccion tipoTransaccion;
 private String descripcion;
 @Column (nullable = false)
 private LocalDateTime fechaCreacion;
}
