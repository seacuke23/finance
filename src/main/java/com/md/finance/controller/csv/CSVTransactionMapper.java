package com.md.finance.controller.csv;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Optional;

import com.md.finance.controller.csv.exception.DataFormatException;
import com.md.finance.controller.csv.exception.MissingMappingException;
import com.md.finance.dto.TransactionDTO;
import com.md.finance.model.Transaction;
import com.md.finance.model.TransactionState;

public class CSVTransactionMapper {

	private int dateIndex = -1;
	private int amountIndex = -1;
	private int detail1Index = -1;
	private int detail2Index = -1;
	private CSVTransactionMapping type;

	public CSVTransactionMapper(CSVTransactionMapping type, String[] headers) throws MissingMappingException {
		this.type = type;
		HashMap<String, Integer> headerMap = new HashMap<>();
		for (int i = 0; i < headers.length; i++) {
			headerMap.put(headers[i], i);
		}
		dateIndex = Optional.ofNullable(headerMap.get(type.getDateName()))
				.orElseThrow(() -> getExceptionForMissingField("date"));
		amountIndex = Optional.ofNullable(headerMap.get(type.getAmountName()))
				.orElseThrow(() -> getExceptionForMissingField("amount"));
		detail1Index = Optional.ofNullable(headerMap.get(type.getDetail1Name()))
				.orElseThrow(() -> getExceptionForMissingField("detail1"));
		detail2Index = Optional.ofNullable(headerMap.get(type.getDetail2Name()))
				.orElseThrow(() -> getExceptionForMissingField("detail2"));
	}
	
	public TransactionDTO getTransactionFromInputLine(String[] input) throws DataFormatException {
		try {
			return new TransactionDTO(null, type.getDateFormat().parse(input[dateIndex]), Double.parseDouble(input[amountIndex]), input[detail1Index], input[detail2Index], TransactionState.UNVERIFIED, null, null, null);
		} catch (Exception e) {
			throw new DataFormatException("Unable to parse transaction info.", e);
		}
	}

	private static MissingMappingException getExceptionForMissingField(String fieldName) {
		return new MissingMappingException("Couldn't find field for " + fieldName);
	}
}
