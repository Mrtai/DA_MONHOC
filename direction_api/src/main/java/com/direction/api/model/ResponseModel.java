package com.direction.api.model;

import java.util.ArrayList;

import com.direction.api.consts.ResultCodeConsts;
import com.direction.common.util.SingletonFactory;


public class ResponseModel {

	private boolean result = true;
	private Integer code = ResultCodeConsts.SUCCESS.getCode();
	private String message = ResultCodeConsts.SUCCESS.getMessage();
	private Object data = new ArrayList<>();

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		if (code == ResultCodeConsts.SUCCESS.getCode()) {
			this.result = true;
			this.message = ResultCodeConsts.SUCCESS.getMessage();
		}
		else if(code == ResultCodeConsts.DATA_NOT_FOUND.getCode()){
			this.result = true;
			this.message = ResultCodeConsts.DATA_NOT_FOUND.getMessage();
		}
		else {
			this.result = false;
			this.message = ResultCodeConsts.getMessageByCode(code);
		}
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String toString() {
		return SingletonFactory.getGsonInstance().toJson(this);
	}
	
}
