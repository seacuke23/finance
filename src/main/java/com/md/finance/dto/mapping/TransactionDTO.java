package com.md.finance.dto.mapping;

import java.util.Date;

import lombok.Data;

@Data
public class TransactionDTO {
	private final Long id;
	private final Date date;
}
