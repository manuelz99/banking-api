package com.manuelz.bankingapi.repositories;

import com.manuelz.bankingapi.models.Account;
import com.manuelz.bankingapi.models.enums.Moneda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findByUserId(Long userID);
    boolean existsByUserIdAndMoneda(Long userId, Moneda moneda);
    Optional<Account> findByCbu(String cbu);
    Optional<Account> findByAlias(String alias);

    boolean existsByDni(String dni);

    boolean existsByAlias(String alias);
}
