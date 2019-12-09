package com.direction.api.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.direction.api.consts.GlobalConsts;
import com.direction.api.consts.ResultCodeConsts;
import com.direction.api.model.ResponseModel;
import com.direction.api.service.IUserService;
import com.direction.common.entity.UserEntity;
import com.direction.common.util.CommonUtil;
import com.direction.common.util.SingletonFactory;
import com.direction.common.util.Util;
import com.google.gson.Gson;

@Service
public class UserService extends AbstractBasicService implements IUserService {

	private static final Logger log = Logger.getLogger(UserService.class);
	

	@Override
	public List<UserEntity> getListUser() {
		return this.userDAO.findAll(GlobalConsts.MAX_RECORD);
	}

	@Override
	public UserEntity getUserByUserId(Integer userId) {
		return this.userDAO.findById(userId);
	}

	@Override
	public UserEntity getUserByUserName(String username) {
		return this.userDAO.getUniqueBy(UserEntity.USERNAME, username);
	}

	@Override
	public UserEntity getUserByEmail(String email) {
		return this.userDAO.getUniqueBy(UserEntity.EMAIL, email);
	}

	@Override
	public UserEntity getUser(String username, String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(UserEntity.USERNAME, username);
		params.put(UserEntity.PASSWORD, password);
		return this.userDAO.getUniqueBy(params);
	}

	@Override
	public ResponseModel addUser(UserEntity model) {
		ResponseModel resp = new ResponseModel();
		try {
			if (checkEmailExist(model.getEmail()) == 1) {
				resp.setCode(ResultCodeConsts.ERROR_NEW_USER.getCode());
				resp.setMessage("Email is alredy");
				return resp;
			}
			if (checkEmailExist(model.getEmail()) == 2) {
				resp.setCode(ResultCodeConsts.ERROR_NEW_USER.getCode());
				resp.setMessage("User has been locked");
				return resp;
			}
			model.setPassword(Util.encryptPassword(model.getPassword()));
			model.setStatus("ACT");
			if(userDAO.persist(model)){
				resp.setCode(ResultCodeConsts.SUCCESS.getCode());
				resp.setData(model);
			}
			else{
				resp.setCode(ResultCodeConsts.ERROR_NEW_USER.getCode());
			}
		} catch (Exception e) {
			log.error(CommonUtil.convertExceptionToString(e));
			resp.setCode(ResultCodeConsts.SYSTEM_ERROR.getCode());
		}
		
		return resp;
	}

	@Override
	public boolean updateUser(String userId, Map<String, String> params, Integer agentId) {
		boolean result = false;
		try {
			Map<String, Object> convertedParams = this.convertParams(params);
			log.info("Update User Params:: " + this.gson.toJson(convertedParams));
			if (!convertedParams.isEmpty()) {
				result = this.userDAO.update(convertedParams, UserEntity.CODE, userId);
			}
		} catch (Exception ex) {
			log.error(Util.convertExceptionToString(ex));
		}
		return result;
	}

	private Map<String, Object> convertParams(Map<String, String> params) {
		Map<String, Object> convertedParams = new HashMap<String, Object>();
		try {
			String username = params.get(UserEntity.USERNAME);
			if (username != null) {
				convertedParams.put(UserEntity.USERNAME, username);
			}
		} catch (Exception ex) {
			log.error(Util.convertExceptionToString(ex));
		}
		try {
			String email = params.get(UserEntity.EMAIL);
			if (email != null) {
				convertedParams.put(UserEntity.EMAIL, email);
			}
		} catch (Exception ex) {
			log.error(Util.convertExceptionToString(ex));
		}
		

		
		return convertedParams;
	}

	@Override
	public boolean deleteUserByUserId(Integer userId) {
		boolean result = false;
		
		return result;
	}

	@Override
	public Map<String, Integer> getPermissions(String username) {
		Map<String, Integer> permissions = new HashMap<String, Integer>();
		try {

		} catch (Exception ex) {
			log.error(Util.convertExceptionToString(ex));
		}
		return permissions;
	}

	@Override
	public UserEntity getUserByUsernameOrEmail(String userNameOrEmail) {
		UserEntity user = null;
		try {
			user = this.getUserByUserName(userNameOrEmail);
			if (user == null) {
				user = this.getUserByEmail(userNameOrEmail);
			}
		} catch (Exception ex) {
			log.error(Util.convertExceptionToString(ex));
		}
		return user;
	}

	private Integer checkEmailExist(String email) {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put(UserEntity.EMAIL, email);
		UserEntity entity = userDAO.getFirstEntityByConditions(conditions, UserEntity.CREATE_DATE, "DESC",
				UserEntity.class);

		if (entity != null) {
			if (entity.getStatus().equals(GlobalConsts.STATUS_ACTIVED))
				return 1;
			if (entity.getStatus().equals(GlobalConsts.STATUS_LOCKED))
				return 2;
		}
		return 0;
	}

	@Override
	public ResponseModel login(UserEntity entity) {
		ResponseModel resp = new ResponseModel();
		try{
			UserEntity user = null;
			String username = entity.getUsername();
			String email = entity.getEmail();
			user = userDAO.getUserInfoByUsername(username);
			
			if (user == null) {
				user = userDAO.getUserInfoByEmail(email);
				}

			if (user == null) {
				resp.setCode(ResultCodeConsts.USERNAME_OR_EMAIL_INVALID.getCode());
				return resp;
			} 	
			
			String encryptpassword = Util.encryptPassword(entity.getPassword());
			if(!user.getPassword().equalsIgnoreCase(encryptpassword)){
				resp.setCode(ResultCodeConsts.PASSWORD_INVALID.getCode());
				return resp;
			}
			
			String token = userDAO.getTokenByUsername(username);
			if(token != null){
				resp.setCode(ResultCodeConsts.SUCCESS.getCode());
				resp.setData(user);
			}
			else{
				user.setAccessToken(userDAO.generateToken());
				if(userDAO.update(user)){
					resp.setCode(ResultCodeConsts.SUCCESS.getCode());
					resp.setData(user);
				}
				else{
					resp.setCode(ResultCodeConsts.DATABASE_ERROR.getCode());
				}
			}
			
		}catch (Exception e) {
			resp.setCode(ResultCodeConsts.SYSTEM_ERROR.getCode());
			log.error(CommonUtil.convertExceptionToString(e));
		}
		return resp;
	}

}
