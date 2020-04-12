package com.md.finance.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.md.finance.dto.TransactionDTO;
import com.md.finance.dto.mapping.TransactionMapper;
import com.md.finance.model.Transaction;
import com.md.finance.model.TransactionCategory;
import com.md.finance.model.TransactionState;
import com.md.finance.repository.TransactionRepository;

@Service
@Transactional
public class TransactionService {
	@Autowired
	private TransactionRepository repo;
	@Autowired
	private TransactionMapper mapper;
	
	public void addTransactions(List<TransactionDTO> transactions) {
		List<Transaction> txs = transactions.stream().map(x->mapper.transactionDTOToTransaction(x)).collect(Collectors.toList());
		repo.saveAll(txs);
	}
	
	public List<TransactionDTO> findUnverifiedTransactions(){
		return repo.findByState(TransactionState.UNVERIFIED).stream().map(x->mapper.transactionToTransactionDTO(x)).collect(Collectors.toList());
	}
}
