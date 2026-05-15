package com.manuelz.bankingapi.mapper;

import com.manuelz.bankingapi.dtos.request.AccountRequestDTO;
import com.manuelz.bankingapi.dtos.response.AccountResponseDTO;
import com.manuelz.bankingapi.models.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE= Mappers.getMapper(AccountMapper.class);
    

    AccountResponseDTO toDTO(Account account);
    @Mapping(target = "id", ignore = true)
    Account toEntity(AccountRequestDTO accountRequestDTO);
}
