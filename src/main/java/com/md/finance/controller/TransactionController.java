package com.md.finance.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.md.finance.dto.mapping.TransactionDTO;

@RestController
public class TransactionController {

	@GetMapping("/test")
	public TransactionDTO getTransaction() {
		return new TransactionDTO(1l, new Date());
	}
}
