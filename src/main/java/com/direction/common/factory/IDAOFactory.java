package com.direction.common.factory;

import com.direction.common.dao.IPlaceDAO;
import com.direction.common.dao.IUserDAO;

public interface IDAOFactory {

	IUserDAO createUserDao();
	
	IPlaceDAO createPlaceDao();
	
}