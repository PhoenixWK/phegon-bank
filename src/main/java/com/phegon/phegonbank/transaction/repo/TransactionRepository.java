package com.phegon.phegonbank.transaction.repo;

import com.phegon.phegonbank.transaction.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
    * Repository interface for managing Transaction entities.
    findByAccount_AccountNumber: _AccountNumber means to access the account's accountNumber field
*/

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByAccount_AccountNumber(String accountNumber, Pageable pageable);
    List<Transaction> findByAccount_AccountNumber(String accountNumber);
}
