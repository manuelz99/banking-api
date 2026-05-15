package com.manuelz.bankingapi.mapper;

import com.manuelz.bankingapi.dtos.request.AccountRequestDTO;
import com.manuelz.bankingapi.dtos.request.TransaccionRequestDTO;
import com.manuelz.bankingapi.dtos.response.AccountResponseDTO;
import com.manuelz.bankingapi.dtos.response.TransaccionResponseDTO;
import com.manuelz.bankingapi.models.Account;
import com.manuelz.bankingapi.models.Transaction;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface TransaccionMapper {
    TransaccionMapper INSTANCE= Mappers.getMapper(TransaccionMapper.class);


    TransaccionResponseDTO toDTO(Transaction transaction);
    @Mapping(target = "id", ignore = true)
    Transaction toEntity(TransaccionRequestDTO transaccionRequestDTO);
}
