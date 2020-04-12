package com.md.finance.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date date;
	private double amount;
	private String detail1;
	private String detail2;
	@Enumerated(EnumType.STRING)
	private TransactionState state;
	@ManyToOne(fetch = FetchType.LAZY)
	private Transaction parent;
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	private List<Transaction> children;
	@ManyToOne
	private TransactionCategory category;
}
