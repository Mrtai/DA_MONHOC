package com.direction.api.model;

import java.util.List;

public class LegModel {

	private DistanceModel distance;
	private DurationModel duration;
	private List<StepModel> steps = null;
	private String end_address;
	private LocationModel end_location;
	private String start_address;
	private LocationModel start_location;
	private PolylineModel overview_polyline;
	
	
	
	
	public PolylineModel getOverview_polyline() {
		return overview_polyline;
	}
	public void setOverview_polyline(PolylineModel overview_polyline) {
		this.overview_polyline = overview_polyline;
	}
	public String getEnd_address() {
		return end_address;
	}
	public void setEnd_address(String end_address) {
		this.end_address = end_address;
	}
	public LocationModel getEnd_location() {
		return end_location;
	}
	public void setEnd_location(LocationModel end_location) {
		this.end_location = end_location;
	}
	public String getStart_address() {
		return start_address;
	}
	public void setStart_address(String start_address) {
		this.start_address = start_address;
	}
	public LocationModel getStart_location() {
		return start_location;
	}
	public void setStart_location(LocationModel start_location) {
		this.start_location = start_location;
	}
	public List<StepModel> getSteps() {
		return steps;
	}
	public void setSteps(List<StepModel> steps) {
		this.steps = steps;
	}
	public DistanceModel getDistance() {
		return distance;
	}
	public void setDistance(DistanceModel distance) {
		this.distance = distance;
	}
	public DurationModel getDuration() {
		return duration;
	}
	public void setDuration(DurationModel duration) {
		this.duration = duration;
	}
	
	
}
