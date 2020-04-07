package com.md.finance.repository;

import org.springframework.data.repository.CrudRepository;

import com.md.finance.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
