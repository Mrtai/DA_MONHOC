package com.direction.api.model;


public class StepModel {
	
	private DistanceModel distance;
	private DurationModel duration;
	private PolylineModel polyline;
	private LocationModel start_location;
	private LocationModel end_location;
	private String html_instructions;
	private String maneuver;
	
	
		
	public String getManeuver() {
		return maneuver;
	}
	public void setManeuver(String maneuver) {
		this.maneuver = maneuver;
	}
	public LocationModel getStart_location() {
		return start_location;
	}
	public void setStart_location(LocationModel start_location) {
		this.start_location = start_location;
	}
	public LocationModel getEnd_location() {
		return end_location;
	}
	public void setEnd_location(LocationModel end_location) {
		this.end_location = end_location;
	}
	public String getHtml_instructions() {
		return html_instructions;
	}
	public void setHtml_instructions(String html_instructions) {
		this.html_instructions = html_instructions;
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
	public PolylineModel getPolyline() {
		return polyline;
	}
	public void setPolyline(PolylineModel polyline) {
		this.polyline = polyline;
	}
	
	

}
