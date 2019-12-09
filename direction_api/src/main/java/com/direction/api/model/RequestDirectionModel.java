package com.direction.api.model;

import java.util.List;

import com.direction.common.entity.PlaceEntity;

public class RequestDirectionModel {
	private Integer filter;
	private Double latitudeStart;
	private Double longitudeStart;
	private List<PlaceEntity> data = null;
	public Integer getFilter() {
		return filter;
	}
	public void setFilter(Integer filter) {
		this.filter = filter;
	}
	public Double getLatitudeStart() {
		return latitudeStart;
	}
	public void setLatitudeStart(Double latitudeStart) {
		this.latitudeStart = latitudeStart;
	}
	public Double getLongitudeStart() {
		return longitudeStart;
	}
	public void setLongitudeStart(Double longitudeStart) {
		this.longitudeStart = longitudeStart;
	}
	public List<PlaceEntity> getData() {
		return data;
	}
	public void setData(List<PlaceEntity> data) {
		this.data = data;
	}
	
	
	

}
