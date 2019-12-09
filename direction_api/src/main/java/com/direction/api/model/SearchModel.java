package com.direction.api.model;

import java.io.Serializable;

public class SearchModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String value;
	private Boolean regex;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Boolean getRegex() {
		return regex;
	}
	public void setRegex(Boolean regex) {
		this.regex = regex;
	}
	
	
}
