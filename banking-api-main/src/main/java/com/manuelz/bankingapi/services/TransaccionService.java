package com.manuelz.bankingapi.services;

import com.manuelz.bankingapi.dtos.request.TransaccionRequestDTO;
import com.manuelz.bankingapi.dtos.response.TransaccionResponseDTO;
import com.manuelz.bankingapi.mapper.TransaccionMapper;
import com.manuelz.bankingapi.models.Account;
import com.manuelz.bankingapi.models.Transaction;
import com.manuelz.bankingapi.models.enums.TipoCuenta;
import com.manuelz.bankingapi.models.enums.TipoTransaccion;
import com.manuelz.bankingapi.repositories.TransaccionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
@AllArgsConstructor
public class TransaccionService {
    private final TransaccionRepository transaccionRepository;
    private final AccountService accountService;
    private final TransaccionMapper transaccionMapper;


    public TransaccionResponseDTO transferir(TransaccionRequestDTO transaccionRequestDTO) {
        validaAmbosAlias(transaccionRequestDTO.aliasDestino(), transaccionRequestDTO.aliasOrigen());
        verificaAmbasCuentasMismoTipo(transaccionRequestDTO.aliasDestino(), transaccionRequestDTO.aliasOrigen());

        accountService.debitarSaldo(transaccionRequestDTO.aliasDestino(),transaccionRequestDTO.monto());
        accountService.acreditarSaldo(transaccionRequestDTO.aliasDestino(), transaccionRequestDTO.monto());

        Transaction transaction=transaccionMapper.toEntity(transaccionRequestDTO);
        transaction.setTipoTransaccion(TipoTransaccion.TRANSFERENCIA);
        transaction.setFechaCreacion(LocalDateTime.now());
        transaction.setDescripcion("EXITOSA");
        transaccionRepository.save(transaction);
        TransaccionResponseDTO transaccionResponseDTO=transaccionMapper.toDTO(transaction);
        return transaccionResponseDTO;
    }

    private boolean validaAmbosAlias(String aliasUno, String aliasDos) {
        if (!accountService.validar_existencia_alias(aliasUno) ||
                !accountService.validar_existencia_alias(aliasDos)) {
            return false;
        }
        return true;
    }

    private boolean verificaAmbasCuentasMismoTipo(String aliasUno,String aliasDos){
        TipoCuenta tipoCuentaUno=accountService.devuelveTipoCuentaPorAlias(aliasUno);
        TipoCuenta tipoCuentaDos=accountService.devuelveTipoCuentaPorAlias(aliasDos);
        if(!tipoCuentaUno.equals(tipoCuentaDos)){
            return false;
        }
        return true;
    }
}
