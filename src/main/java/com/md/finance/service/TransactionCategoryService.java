package com.md.finance.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.md.finance.dto.TransactionCategoryDTO;
import com.md.finance.dto.mapping.TransactionCategoryMapper;
import com.md.finance.model.TransactionCategory;
import com.md.finance.repository.TransactionCategoryRepository;

@Service
@Transactional
public class TransactionCategoryService {
	@Autowired
	private TransactionCategoryRepository repo;
	@Autowired
	private TransactionCategoryMapper mapper;

	public List<TransactionCategoryDTO> getCategories() {
		return StreamSupport.stream(repo.findAll().spliterator(), false).map(x -> mapper.entityToDTO(x))
				.collect(Collectors.toList());
	}
	
	//TODO return an optional here but have to change the mapper as well to deal with it
	public TransactionCategory getCategory(Long id) {
		return repo.findById(id).get();
	}
}
