package com.manuelz.bankingapi.models;

import com.manuelz.bankingapi.models.enums.Moneda;
import com.manuelz.bankingapi.models.enums.TipoCuenta;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private Long userId;
    @Column(unique = true,nullable = false,length = 22)
    private String cbu;
    @Column(unique = true,nullable = false)
    private String alias;
    @Enumerated(EnumType.STRING)
    @Column(name ="currency",nullable = false)
    private Moneda moneda;
    @Enumerated(EnumType.STRING)
    @Column(name ="account_type",nullable = false)
    private TipoCuenta tipoCuenta;
    @Column(nullable = false,precision = 15,scale = 2)
    private BigDecimal saldo;
    @Column(name = "created_at",nullable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate(){
        this.fechaCreacion=LocalDateTime.now();
        if(this.saldo==null){
            this.saldo=BigDecimal.ZERO;
        }
    }
}
