package com.md.finance.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.md.finance.controller.csv.CSVTransactionMapper;
import com.md.finance.controller.csv.CSVTransactionMapping;
import com.md.finance.controller.csv.exception.MissingMappingException;
import com.md.finance.dto.TransactionDTO;
import com.md.finance.dto.mapping.TransactionMapper;
import com.md.finance.exception.service.ObjectNotFoundException;
import com.md.finance.model.Transaction;
import com.md.finance.model.TransactionState;
import com.md.finance.service.TransactionService;
import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@Api(value = "trans", description = "the trans API")
@RestController
@Log4j2
public class TransactionController {
	@Autowired
	private TransactionService txService;
	

	private static final SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy");

    @ApiOperation(value = "", nickname = "getTransactions")
	@GetMapping(value="/api/trans", produces= {"application/json"})
	public List<TransactionDTO> getTransactions(@RequestParam Boolean unverified) {
		if(Optional.ofNullable(unverified).orElseGet(()->false)) {
			return txService.findUnverifiedTransactions();
		}
		return new ArrayList<>();
	}

    @ApiOperation(value = "", nickname = "getTransaction")
	@GetMapping(value="/api/trans/{id}", produces= {"application/json"})
	public TransactionDTO getTransaction(@PathVariable Long id) {
		return txService.getTransaction(id).orElseThrow(()->new RuntimeException("id not found"));
	}

    @ApiOperation(value = "", nickname = "updateTransaction")
	@PutMapping(value="/api/trans/{id}", produces= {"application/json"})
	public void updateTransaction(@RequestBody TransactionDTO transaction, @PathVariable Long id) {
		transaction.setId(id);
		try {
			txService.updateTransaction(transaction);
		} catch (ObjectNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	//TODO look out for really big files
    @ApiOperation(value = "", nickname = "uploadTransactions")
	@PostMapping(value="/test", produces= {"application/json"})
	public void uploadTransactions(@RequestParam("fileToUpload") MultipartFile file,@RequestParam("bankType") String bank) {
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
}
