package com.md.finance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.md.finance.dto.TransactionCategoryDTO;
import com.md.finance.service.TransactionCategoryService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class TransactionCategoryController {
	@Autowired
	private TransactionCategoryService service;

	@GetMapping("/api/trans_category")
	public List<TransactionCategoryDTO> getCategories(){
		return service.getCategories();
	}
}
