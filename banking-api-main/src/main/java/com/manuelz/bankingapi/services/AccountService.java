package com.manuelz.bankingapi.services;

import com.manuelz.bankingapi.dtos.request.AccountRequestDTO;
import com.manuelz.bankingapi.dtos.response.AccountResponseDTO;
import com.manuelz.bankingapi.exceptions.CuentaDuplicadaEx;
import com.manuelz.bankingapi.exceptions.CuentaInexistente;
import com.manuelz.bankingapi.exceptions.CuentaVaciaEx;
import com.manuelz.bankingapi.exceptions.SaldoInsuficienteEx;
import com.manuelz.bankingapi.mapper.AccountMapper;
import com.manuelz.bankingapi.models.Account;
import com.manuelz.bankingapi.models.enums.TipoCuenta;
import com.manuelz.bankingapi.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class AccountService {
    private AccountRepository accountRepository;
    private AccountMapper accountMapper;

    public AccountResponseDTO crear_cuenta(AccountRequestDTO accountRequestDTO) {
        if(accountRepository.existsByUserIdAndMoneda(accountRequestDTO.userid(),accountRequestDTO.moneda())){
            throw new CuentaDuplicadaEx("El usuario ya posee una cuenta en " + accountRequestDTO.moneda());
        }
        Account account=accountMapper.toEntity(accountRequestDTO);
        account.setAlias("PRUEBA.UNO.YA");
        account.setCbu("0170001520000001234567");
        accountRepository.save(account);
        return accountMapper.toDTO(account);
    }

    public List<AccountResponseDTO> listarCuentasPorUsuario(Long idCliente){
        List<Account> cuentasActivas=accountRepository.findByUserId(idCliente);
        if(cuentasActivas.isEmpty()){
            throw new CuentaVaciaEx("El cliente no posee cuentas a su nombre");
        }
        return cuentasActivas.stream()
                .map(cuenta -> accountMapper.toDTO(cuenta)).toList();
    }

    public AccountResponseDTO obtenerPorIdCuenta(Long cuentaId){
        Account cuenta=accountRepository.findById(cuentaId).orElseThrow(() -> new CuentaInexistente("No existe cuenta con ese ID"));
       AccountResponseDTO accountDTO=accountMapper.toDTO(cuenta);
       return accountDTO;
    }

    public void acreditarSaldo(String alias, BigDecimal monto){
        Account account=accountRepository.findByAlias(alias).orElseThrow(() -> new CuentaInexistente("No existe cuenta con ese alias"));
        account.setSaldo(account.getSaldo().add(monto));
    }

    public void debitarSaldo(String alias,BigDecimal monto){
        Account account=accountRepository.findByAlias(alias).orElseThrow(() ->new CuentaInexistente("No existe cuenta con ese alias."));
        if(account.getSaldo().compareTo(monto)<0){
            throw new SaldoInsuficienteEx("No tenes fondos suficientes para realizar esta operacion");
        }
        account.setSaldo(account.getSaldo().subtract(monto));
    }

    public boolean validar_existencia_alias(String alias) {
        return accountRepository.existsByAlias(alias);
    }

    public TipoCuenta devuelveTipoCuentaPorAlias(String alias){
        Account account=accountRepository.findByAlias(alias).orElseThrow(() -> new CuentaInexistente("No hay cuentas con ese alias."));
        return account.getTipoCuenta();
    }

}
