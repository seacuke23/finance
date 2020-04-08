package com.md.finance.dto.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.md.finance.dto.TransactionDTO;
import com.md.finance.model.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

	@Mapping(source="parent.id", target="parent")
	TransactionDTO transactionToTransactionDTO(Transaction tx);
	
	@Mapping(target="parent", ignore=true)
	@Mapping(target="children", ignore=true)
	Transaction transactionDTOToTransaction(TransactionDTO tx);
	
	default List<Long> map(List<Transaction> value){
		List<Long> mapped = new ArrayList<>();
		if(value != null) {
			mapped = value.stream().map(x -> x.getId()).collect(Collectors.toList());
		}
		return mapped;
	}
}
