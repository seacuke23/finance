package com.md.finance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.md.finance.model.Transaction;
import com.md.finance.model.TransactionState;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

	@Query("select t from Transaction t where t.state = :state order by t.date desc, id asc")
	List<Transaction> findByState(@Param("state")TransactionState state);
}
