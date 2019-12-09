package com.direction.common.factory.impl;

import com.direction.common.dao.IPlaceDAO;
import com.direction.common.dao.IUserDAO;
import com.direction.common.dao.impl.PlaceDAO;
import com.direction.common.dao.impl.UserDAO;
import com.direction.common.factory.IDAOFactory;

public class DAOFactory implements IDAOFactory {

	private DAOFactory() {
    }

    private static class Loader {
        private static final DAOFactory INSTANCE = new DAOFactory();
    }

    public static DAOFactory getInstance() {
        return Loader.INSTANCE;
    }

	@Override
	public IUserDAO createUserDao() {
		return UserDAO.getInstance();
	}

	@Override
	public IPlaceDAO createPlaceDao() {
		return PlaceDAO.getInstance();
	}
	
}
