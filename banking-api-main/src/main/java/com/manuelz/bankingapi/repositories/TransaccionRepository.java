package com.manuelz.bankingapi.repositories;

import com.manuelz.bankingapi.models.Transaction;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaction,Long> {

}
