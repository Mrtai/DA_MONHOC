package com.direction.api.model;

public class EdgeModel {

	private Integer start,end;
	private Long distance;
	private Long duration;
	private LegModel leg;
	private PolylineModel overview_polyline;
	
	
	
	
	public PolylineModel getOverview_polyline() {
		return overview_polyline;
	}
	public void setOverview_polyline(PolylineModel overview_polyline) {
		this.overview_polyline = overview_polyline;
	}
	public LegModel getLeg() {
		return leg;
	}
	public void setLeg(LegModel leg) {
		this.leg = leg;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
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
	
	
}
