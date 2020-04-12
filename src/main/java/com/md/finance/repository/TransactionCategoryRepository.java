package com.md.finance.repository;

import org.springframework.data.repository.CrudRepository;

import com.md.finance.model.Transaction;
import com.md.finance.model.TransactionCategory;

public interface TransactionCategoryRepository extends CrudRepository<TransactionCategory, Long> {

}
