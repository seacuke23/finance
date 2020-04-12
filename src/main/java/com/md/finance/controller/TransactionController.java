package com.md.finance.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.md.finance.controller.csv.CSVTransactionMapper;
import com.md.finance.controller.csv.CSVTransactionMapping;
import com.md.finance.controller.csv.exception.MissingMappingException;
import com.md.finance.dto.TransactionDTO;
import com.md.finance.dto.mapping.TransactionMapper;
import com.md.finance.model.Transaction;
import com.md.finance.model.TransactionState;
import com.md.finance.service.TransactionService;
import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class TransactionController {
	@Autowired
	private TransactionMapper mapper;
	@Autowired
	private TransactionService txService;
	

	private static final SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy");

	@GetMapping("/test")
	public List<TransactionDTO> getTransaction() {
		ArrayList<TransactionDTO> transactions = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Transaction t = new Transaction();
			t.setId(1234l + i);
			t.setDate(new Date());
			t.setDetail1("det1" + i);
			t.setDetail2("det2" + i);
			t.setState(TransactionState.UNVERIFIED);
			transactions.add(mapper.transactionToTransactionDTO(t));
		}
		return transactions;
		// return TransactionDTO.builder().id(1l).date(new
		// Date()).amount(123).detail1("detail1").detail2("detail2").state(TransactionState.UNVERIFIED).build();
		// return new TransactionDTO(1l, new Date(), 123, "detail1", "detail2",
		// TransactionState.UNVERIFIED, null, null);
	}

	@PostMapping("/test")
	public void getTheFile(@RequestParam("fileToUpload") MultipartFile file,@RequestParam("bankType") String bank, RedirectAttributes redirectAttributes) {
		try {
			log.debug("Transaction file uploaded of type '{}'", bank);
			log.info("infolog");
			CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()));
			ArrayList<TransactionDTO> txs = new ArrayList<>();
			String[] nextLine = reader.readNext();
			//TODO check null on transaction mapping lookup
			CSVTransactionMapper mapper = new CSVTransactionMapper(CSVTransactionMapping.getByName(bank), nextLine);
			
			while ((nextLine = reader.readNext()) != null) {
				try {
					txs.add(mapper.getTransactionFromInputLine(nextLine));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println(transactionFromCSVEntry(nextLine));
			}
			txService.addTransactions(txs);

		} catch (CsvValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MissingMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private TransactionDTO transactionFromCSVEntry(String[] csv) throws ParseException {
		TransactionDTO tx = new TransactionDTO();

		tx.setAmount(Double.parseDouble(csv[6]));
		tx.setDate(SDF.parse(csv[2]));
		tx.setDetail1(csv[4]);
		tx.setDetail2(csv[5]);
		tx.setState(TransactionState.UNVERIFIED);
		return tx;
	}
}
