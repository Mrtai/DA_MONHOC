package com.direction.common.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.direction.common.constants.Status;
import com.direction.common.dao.IUserDAO;
import com.direction.common.entity.UserEntity;
import com.direction.common.util.CommonUtil;
import com.direction.common.util.HashUtil;

import jdk.nashorn.internal.runtime.GlobalConstants;

public class UserDAO extends BasicDAO<UserEntity, Integer> implements IUserDAO {

	

	private static final Logger log = Logger.getLogger(UserDAO.class);

	private UserDAO() {
	}

	private static class Loader {
		private static final UserDAO INSTANCE = new UserDAO();
	}

	public static UserDAO getInstance() {
		return Loader.INSTANCE;
	}

	@Override
	public boolean checkUserActivedByUsername(String username) {
		UserEntity userInfo = getUserInfoByUsername(username);
		if (userInfo == null) {
			return false;
		}
		
		if (Status.USER.ACT.equals(userInfo.getStatus())) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean checkUserActivedById(Integer userId) {
		UserEntity userInfo = findById(userId);
		if (userInfo == null) {
			return false;
		}
		
		if (Status.USER.ACT.equals(userInfo.getStatus())) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean checkUserActivedByEmail(String email) {
		UserEntity userInfo = getUserInfoByEmail(email);
		if (userInfo == null) {
			return false;
		}
		
		if (Status.USER.ACT.equals(userInfo.getStatus())) {
			return true;
		}
		
		return false;
	}

	@Override
	public UserEntity getUserInfoByUsername(String username) {
		UserEntity entity = null;
		try {
			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put("username", username);
			entity = getFirstEntityByConditions(conditions,UserEntity.CREATE_DATE,"desc", UserEntity.class);
			if (entity == null) {
				log.warn("User is not found by username -> " + username);
			}
		} catch (Exception e) {
			log.error(CommonUtil.convertExceptionToString(e));
		}
		return entity;
	}

	@Override
	public UserEntity getUserInfoByEmail(String email) {
		UserEntity entity = null;
		try {
			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put("email", email);
			entity = getFirstEntityByConditions(conditions,UserEntity.CREATE_DATE,"desc", UserEntity.class);
			if (entity == null) {
				log.warn("User is not found by email -> " + email);
			}
		} catch (Exception e) {
			log.error(CommonUtil.convertExceptionToString(e));
		}
		return entity;
	}
	@Override
	public UserEntity getUserInfoById(long Id) {
		UserEntity entity = null;
		try {
			Map<String, Object> conditions = new HashMap<String,Object>();
			conditions.put(UserEntity.CODE, Id);
			entity = getFirstEntityByConditions(conditions, UserEntity.class);
			if (entity == null) {
				log.warn("User is not found by id -> " + Id);
			}
		} catch (Exception e) {
			log.error(CommonUtil.convertExceptionToString(e));
		}
		return entity;
	}

	@Override
	public boolean checkTokenValid(String token) {
		boolean ret = false;

		if (CommonUtil.isNullOrEmpty(token)) {
			return false;
		}
		try {
			Map<String, Object> conditions = new HashMap<>();
			conditions.put(UserEntity.ACCESS_TOKEN, token);
			UserEntity entity = getFirstEntityByConditions(conditions, UserEntity.class);
			if(entity != null){
				ret = true;
			}
		} catch (Exception e) {
			log.error("Token could not found. Value of token: " + token);
		}
		return ret;
	}

	@Override
	public String generateToken() {
		Date date = new Date();
		String token = HashUtil.MD5(String.valueOf(date.getTime()));
		return token;
	}

	@Override
	public String getTokenByUsername(String username) {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put(UserEntity.USERNAME, username);
		UserEntity entity = getFirstEntityByConditions(conditions, UserEntity.class);
		if(entity != null && entity.getAccessToken() != null){
			return entity.getAccessToken();
		}
		return null;
	}

	@Override
	public UserEntity getUserInfoByToken(String token) {
		UserEntity entity = null;
		try {
			Map<String, Object> conditions = new HashMap<>();
			conditions.put(UserEntity.ACCESS_TOKEN, token);
			entity = getFirstEntityByConditions(conditions, UserEntity.class);
		} catch (Exception e) {
			log.error(CommonUtil.convertExceptionToString(e));
		}
		return entity;
		
	}

	
	
}