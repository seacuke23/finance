package com.md.finance.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.md.finance.model.Transaction;
import com.md.finance.model.TransactionState;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionDTO {
	private Long id;
	private Date date;
	private double amount;
	private String detail1;
	private String detail2;
	private TransactionState state;
	private Long parent;
	private List<Long> children;
}
