package com.direction.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.direction.common.constants.TableName;;
/**
 * The persistent class for the "EDL_PROFIL" database table.
 * 
 */
@Entity
@Table(name = TableName.USER)
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final transient String CODE = "CODE";
	public static final transient String EMAIL = "EMAIL";
	public static final transient String USERNAME = "USERNAME";
	public static final transient String PASSWORD = "PASSWORD";
	public static final transient String STATUS = "STATUS";
	public static final transient String CREATE_DATE = "CREATE_DATE";
	public static final transient String UPDATE_DATE = "UPDATE_DATE";
	public static final transient String ACCESS_TOKEN = "ACCESS_TOKEN";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = CODE)
	private Integer id;

	@Column(name = USERNAME)
	private String username;

	@Column(name = STATUS)
	private String status;

	@Column(name = EMAIL)
	private String email;

	@Column(name = PASSWORD)
	private String password;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = UPDATE_DATE)
	private Date updatedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = CREATE_DATE)
	private Date createdDate;
	
	@Column(name = ACCESS_TOKEN)
	private String accessToken;
	
	

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	 
	
}