package com.nekromant.finance.repository;

import com.nekromant.finance.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByClientId(Long clientId);
}
