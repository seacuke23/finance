package com.md.finance.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Date date;
	
	private double amount;
	
	private String detail1;
	private String detail2;
	private TransactionState state;
	@ManyToOne(fetch=FetchType.LAZY)
	private Transaction parent;
	@OneToMany(mappedBy="parent", fetch=FetchType.LAZY)
	private List<Transaction> splits;
}
