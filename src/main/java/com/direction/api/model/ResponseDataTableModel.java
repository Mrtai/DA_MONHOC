package com.direction.api.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseDataTableModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int draw = 0;
	private int recordsTotal = 0;
	private int recordsFiltered = 0;
	private String error;
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	private Object data = new ArrayList<>();
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
