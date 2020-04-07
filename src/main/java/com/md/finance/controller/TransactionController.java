package com.md.finance.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.md.finance.dto.mapping.TransactionDTO;
import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

@RestController
public class TransactionController {

	@GetMapping("/test")
	public TransactionDTO getTransaction() {
		return new TransactionDTO(1l, new Date());
	}
	
	@PostMapping("/test")
	public void getTheFile(@RequestParam("fileToUpload") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		try {
			CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()));

		     String [] nextLine;
		     while ((nextLine = reader.readNext()) != null) {
		        // nextLine[] is an array of values from the line
		    	 for(String s : nextLine) {
		    		 System.out.print(s + "-=-");
		    	 }
		        System.out.println();
		     }
		} catch (CsvValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		file.
	}
}
