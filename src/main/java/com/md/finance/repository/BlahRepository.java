package com.md.finance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.md.finance.model.Blah;

@Repository
public interface BlahRepository extends CrudRepository<Blah, Long> {

}
