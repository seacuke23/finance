package com.md.finance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Blah {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String value;

	public Blah() {}
	
	public Blah(long id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
