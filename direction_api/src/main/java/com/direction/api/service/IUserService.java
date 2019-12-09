package com.direction.api.service;

import java.util.List;
import java.util.Map;

import com.direction.api.model.ResponseModel;
import com.direction.common.entity.UserEntity;


public interface IUserService {

	List<UserEntity> getListUser();

	UserEntity getUserByUserId(Integer userId);

	UserEntity getUserByUserName(String username);

	UserEntity getUserByEmail(String email);

	UserEntity getUser(String username, String password);

	ResponseModel addUser(UserEntity entity);
	ResponseModel login(UserEntity entity);

	boolean updateUser(String userId, Map<String, String> params, Integer agentId);

	boolean deleteUserByUserId(Integer userId);

	Map<String, Integer> getPermissions(String username);

	UserEntity getUserByUsernameOrEmail(String userNameOrEmail);
}
