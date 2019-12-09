package com.direction.api.model;

import java.util.List;

public class MapModel {

	private List<RouteModel> routes = null;
	private String status;
	
	public List<RouteModel> getRoutes() {
		return routes;
	}
	public void setRoutes(List<RouteModel> routes) {
		this.routes = routes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
