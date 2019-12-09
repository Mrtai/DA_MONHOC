package com.direction.api.consts;

public interface RequestMappingConsts {

	public static final String AUTHENTICATED = "authenticated";
	public static final String CONSUMES = "application/json; charset=utf-8";
	public static final String PRODUCES = "application/json; charset=utf-8";

	/** for authen controller */
	public static final String LOGIN = "/login";
	public static final String CHANGE_ACT = AUTHENTICATED + "/change-active";
	public static final String USER_ADD = "/user-add";
	public static final String USER_DELETE = AUTHENTICATED + "/delete-user";
	public static final String GET_MY_PROFILE = AUTHENTICATED+"/profile";
	/** for place */
	public static final String PLACE = AUTHENTICATED + "/place";
	public static final String PLACE_BY_ID = PLACE +"/{place-id}";
	public static final String GET_API = AUTHENTICATED + "/api-direction";
	public static final String RANDOM_PLACE = AUTHENTICATED + "/random-place";
}
