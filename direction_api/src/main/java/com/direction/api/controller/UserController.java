package com.direction.api.controller;

import java.text.ParseException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.direction.api.consts.ControllerConsts;
import com.direction.api.consts.RequestMappingConsts;
import com.direction.api.consts.ResultCodeConsts;
import com.direction.api.model.ResponseModel;
import com.direction.api.service.impl.UserService;
import com.direction.common.entity.UserEntity;
import com.direction.common.util.CommonUtil;
import com.direction.common.util.SingletonFactory;
import com.google.gson.Gson;

@RestController
@RequestMapping(produces = ControllerConsts.PRODUCES)
public class UserController {

	private static final Logger log = Logger.getLogger(UserController.class);
	@Autowired
	ServletContext context;
	
	
	@Autowired
	private UserService userService;
	
	private Gson gson = SingletonFactory.getGsonInstance();
	
	@RequestMapping(value = RequestMappingConsts.USER_ADD, method = RequestMethod.POST)
	@ResponseBody
	@ExceptionHandler
	public ResponseModel addUser(@RequestBody String model)throws ParseException{
		ResponseModel resp = new ResponseModel();
		try {
			//log.info("Body: " + model);
			UserEntity entity = gson.fromJson(model, UserEntity.class);
			resp =  userService.addUser(entity);
		} catch (Exception e) {
			log.error(CommonUtil.convertExceptionToString(e));
			resp.setCode(ResultCodeConsts.SYSTEM_ERROR.getCode());
		}
		return resp;
	}
	@RequestMapping(value = RequestMappingConsts.LOGIN, method = RequestMethod.POST)
	@ResponseBody
	@ExceptionHandler
	public ResponseModel login(@RequestBody String model)throws ParseException{
		ResponseModel resp = new ResponseModel();
		try {
			//log.info("Body: " + model);
			UserEntity entity = gson.fromJson(model, UserEntity.class);
			resp =  userService.login(entity);
		} catch (Exception e) {
			log.error(CommonUtil.convertExceptionToString(e));
			resp.setCode(ResultCodeConsts.SYSTEM_ERROR.getCode());
		}
		return resp;
	}
	@RequestMapping(value = RequestMappingConsts.USER_DELETE, method = RequestMethod.DELETE)
	@ResponseBody
	@ExceptionHandler
	public ResponseModel deleteUser(HttpServletRequest  req)throws ParseException{
		ResponseModel resp=new ResponseModel();
		try {
			if((Boolean)req.getAttribute("check"))
			{
				Integer userId=(Integer)req.getAttribute("user-id");
				if(userService.deleteUserByUserId(userId)){
					resp.setCode(ResultCodeConsts.SUCCESS.getCode());
					return resp;
				}
				else{
					resp.setCode(ResultCodeConsts.CANNOT_DELETE.getCode());
					return resp;
				}
			}else{
				resp.setCode(ResultCodeConsts.DATABASE_ERROR.getCode());
				return resp ;
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error(CommonUtil.convertExceptionToString(e));
			resp.setCode(ResultCodeConsts.SYSTEM_ERROR.getCode());
		}
		return resp;
	}
	
	@RequestMapping(value = RequestMappingConsts.GET_MY_PROFILE, method = RequestMethod.GET)
	@ResponseBody
	@ExceptionHandler
	public ResponseModel getProfile(HttpServletRequest  req)throws ParseException{
		ResponseModel resp=new ResponseModel();
		try {
			if((Boolean)req.getAttribute("check"))
			{
				Integer userId=(Integer)req.getAttribute("user-id");
				resp.setCode(ResultCodeConsts.SUCCESS.getCode());
				resp.setData(userService.getUserByUserId(userId));
			}else{
				resp.setCode(ResultCodeConsts.USER_NOT_EXIST.getCode());
				return resp ;
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error(CommonUtil.convertExceptionToString(e));
			resp.setCode(ResultCodeConsts.SYSTEM_ERROR.getCode());
		}
		return resp;
	}
//	@RequestMapping(value =  RequestMappingConsts.GET_AVATAR, method = RequestMethod.GET)
//	public void Pdf(HttpServletResponse response, @PathVariable("avatar-id") String avatarId) throws Exception {
//		myProfileService.getAvatar(response,avatarId);
//	}

}
