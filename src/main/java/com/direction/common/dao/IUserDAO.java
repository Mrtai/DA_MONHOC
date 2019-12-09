package com.direction.common.dao;

import com.direction.common.entity.UserEntity;

public interface IUserDAO extends IBasicDAO<UserEntity, Integer> {

	boolean checkUserActivedByUsername(String username);
	
	boolean checkUserActivedById(Integer userId);
	
	boolean checkUserActivedByEmail(String email);
	
	UserEntity getUserInfoByUsername(String username);

	UserEntity getUserInfoByEmail(String email);
	
	UserEntity getUserInfoByToken(String token);
	
	UserEntity getUserInfoById(long Id);
	
	boolean checkTokenValid(String token);
	
	String generateToken();
	
	String getTokenByUsername(String username);
	
//	boolean checkNewPasswordValid(String username, String newPassword);
	
//	boolean changePassword(String username, String password, String newPassword);
	
}
