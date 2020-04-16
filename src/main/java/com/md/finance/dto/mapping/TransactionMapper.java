package com.md.finance.dto.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.md.finance.dto.TransactionDTO;
import com.md.finance.model.Transaction;
import com.md.finance.service.TransactionCategoryService;

@Mapper(componentModel = "spring", uses=TransactionCategoryService.class)
public interface TransactionMapper {

	@Mapping(source="parent.id", target="parent")
	@Mapping(source="category.id", target="category")
	TransactionDTO transactionToTransactionDTO(Transaction tx);
	
	@Mapping(target="parent", ignore=true)
	@Mapping(target="children", ignore=true)
	@Mapping(nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE, target="category")
	Transaction transactionDTOToTransaction(TransactionDTO tx);
	
	@Mapping(target="parent", ignore=true)
	@Mapping(target="children", ignore=true)
	@Mapping( nullValueCheckStrategy=NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.SET_TO_NULL, target="category")
	void updateTransactionFromDTO(TransactionDTO dto, @MappingTarget Transaction trans);
	
	
	default List<Long> map(List<Transaction> value){
		List<Long> mapped = new ArrayList<>();
		if(value != null) {
			mapped = value.stream().map(x -> x.getId()).collect(Collectors.toList());
		}
		return mapped;
	}
}
