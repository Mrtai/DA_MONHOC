package com.direction.api.model;

import java.io.Serializable;
import java.util.Map;

import com.direction.common.entity.UserEntity;


public class BasicInfoSession implements Serializable {

	private static final long serialVersionUID = 1L;

	private UserEntity user;
	private Map<String, Integer> permissions;
	private String defaultPassword;
	private String language;
	private Integer agentId;

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Map<String, Integer> getPermissions() {
		return permissions;
	}

	public void setPermissions(Map<String, Integer> permissions) {
		this.permissions = permissions;
	}

	public String getDefaultPassword() {
		return defaultPassword;
	}

	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
