package com.direction.api.model;

import java.util.List;

public class RouteModel {

	private List<LegModel> legs = null;
	private PolylineModel overview_polyline;
	private Long distance;
	private Long duration;
	
	private int filter;
	
	

	public int getFilter() {
		return filter;
	}

	public void setFilter(int filter) {
		this.filter = filter;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public List<LegModel> getLegs() {
		return legs;
	}

	public void setLegs(List<LegModel> legs) {
		this.legs = legs;
	}
	
	public PolylineModel getOverview_polyline() {
		return overview_polyline;
	}

	public void setOverview_polyline(PolylineModel overview_polyline) {
		this.overview_polyline = overview_polyline;
	}
	
	
	
	
}
