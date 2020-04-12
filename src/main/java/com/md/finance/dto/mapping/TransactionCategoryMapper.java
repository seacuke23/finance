package com.md.finance.dto.mapping;

import org.mapstruct.Mapper;

import com.md.finance.dto.TransactionCategoryDTO;
import com.md.finance.model.TransactionCategory;

@Mapper(componentModel = "spring")
public interface TransactionCategoryMapper {

	TransactionCategory dtoToEntity(TransactionCategoryDTO dto);
	TransactionCategoryDTO entityToDTO(TransactionCategory category);
}
