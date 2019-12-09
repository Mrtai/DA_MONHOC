package com.direction.api.model;

import java.io.Serializable;


public class Column implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String data;
	private String name;
	private Boolean searchable;
	private Boolean orderable;
	private SearchModel search;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getSearchable() {
		return searchable;
	}
	public void setSearchable(Boolean searchable) {
		this.searchable = searchable;
	}
	public Boolean getOrderable() {
		return orderable;
	}
	public void setOrderable(Boolean orderable) {
		this.orderable = orderable;
	}
	public SearchModel getSearch() {
		return search;
	}
	public void setSearch(SearchModel search) {
		this.search = search;
	}
	
	
	
	
}
