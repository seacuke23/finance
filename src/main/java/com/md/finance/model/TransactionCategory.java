package com.md.finance.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class TransactionCategory {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		private String name;
}
