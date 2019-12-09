package com.direction.api.controller;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.direction.api.consts.RequestMappingConsts;
import com.direction.api.consts.ResultCodeConsts;
import com.direction.api.model.RequestDirectionModel;
import com.direction.api.model.ResponseModel;
import com.direction.api.service.IPlaceService;
import com.direction.common.util.CommonUtil;
import com.direction.common.util.SingletonFactory;
import com.google.gson.Gson;


@Controller
@RequestMapping(produces = RequestMappingConsts.PRODUCES)
public class PlaceController {
	
	private static final Logger log= Logger.getLogger(PlaceController.class);
	private Gson gson = SingletonFactory.getGsonInstance();

	@Autowired
	IPlaceService placeService;
	
	@RequestMapping(value = RequestMappingConsts.PLACE, method = RequestMethod.GET)
	@ResponseBody
	@ExceptionHandler
	public ResponseModel getlist()throws ParseException{
		return placeService.getListPlace();
	}
	
	@RequestMapping(value = RequestMappingConsts.RANDOM_PLACE, method = RequestMethod.GET)
	@ResponseBody
	@ExceptionHandler
	public ResponseModel getlistRandom()throws ParseException{
		return placeService.getRandomPlace(5);
	}
	
	@RequestMapping(value = RequestMappingConsts.GET_API, method = RequestMethod.POST)
	@ResponseBody
	@ExceptionHandler
	public ResponseModel getapi(@RequestBody String model)throws ParseException{
		ResponseModel resp = new ResponseModel();
		try {
			RequestDirectionModel rq = gson.fromJson(model, RequestDirectionModel.class);
			return placeService.GTS(rq);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(CommonUtil.convertExceptionToString(e));
			resp.setCode(ResultCodeConsts.SYSTEM_ERROR.getCode());
		}
		return resp;
		
	}
}
