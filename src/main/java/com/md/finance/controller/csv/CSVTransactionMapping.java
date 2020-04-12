package com.md.finance.controller.csv;

import java.text.SimpleDateFormat;

import lombok.Getter;

@Getter
public enum CSVTransactionMapping {
	RBC("RBC", "Transaction Date", "CAD$", "Description 1", "Description 2", new SimpleDateFormat("MM/dd/yyyy")),
	TANGERINE("Tangerine", "Date", "Amount", "Name", "Memo", new SimpleDateFormat("MM/dd/yyyy"));

	private String dateName;
	private String amountName;
	private String detail1Name;
	private String detail2Name;
	private String presentationName;
	private SimpleDateFormat dateFormat;

	private CSVTransactionMapping(String presentationName, String dateName, String amountName, String detail1Name, String detail2Name, SimpleDateFormat dateFormat) {
		this.presentationName = presentationName;
		this.dateName = dateName;
		this.amountName = amountName;
		this.detail1Name = detail1Name;
		this.detail2Name = detail2Name;
		this.dateFormat = dateFormat;
	}

	public static CSVTransactionMapping getByName(String name) {
		for(CSVTransactionMapping m : values()) {
			if(m.getPresentationName().equals(name)) {
				return m;
			}
		}
		return null;
	}
}
