package com.direction.api.service;

import com.direction.api.model.RequestDirectionModel;
import com.direction.api.model.ResponseModel;

public interface IPlaceService {

	ResponseModel getListPlace();
	
	ResponseModel getRandomPlace(Integer quality);
	
	ResponseModel getApiTest();
	
	ResponseModel GTS(RequestDirectionModel model);
}
