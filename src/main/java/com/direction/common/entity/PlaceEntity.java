package com.direction.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.direction.common.constants.TableName;

@Entity
@Table(name = TableName.PLACE)
public class PlaceEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final transient String CODE = "CODE";
	public static final transient String NAME = "NAME";
	public static final transient String ADDRESS = "ADDRESS";
	public static final transient String LATITUDE = "LATITUDE";
	public static final transient String LONGITUDE = "LONGITUDE";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = CODE)
	private Integer placeId;
	
	@Column(name = NAME)
	private String name;
	
	@Column(name = ADDRESS)
	private String address;
	
	@Column(name = LATITUDE)
	private Double latitude;
	
	@Column(name = LONGITUDE)
	private Double longitude;

	public Integer getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	
	
}
