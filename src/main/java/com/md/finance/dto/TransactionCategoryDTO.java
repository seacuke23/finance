package com.md.finance.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("TransactionCategory")
public class TransactionCategoryDTO {
	private Long id;
	private String name;
}
