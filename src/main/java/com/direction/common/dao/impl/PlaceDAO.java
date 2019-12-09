package com.direction.common.dao.impl;

import com.direction.common.dao.IPlaceDAO;
import com.direction.common.entity.PlaceEntity;

public class PlaceDAO extends BasicDAO<PlaceEntity, Integer> implements IPlaceDAO{

	private PlaceDAO(){
		
	}
	
	private static class Loader{
		private static final PlaceDAO INSTANCE = new PlaceDAO();
	}
	
	public static PlaceDAO getInstance(){
		return Loader.INSTANCE;
	}
}
