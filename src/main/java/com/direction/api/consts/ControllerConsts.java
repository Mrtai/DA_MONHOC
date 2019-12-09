package com.direction.api.consts;

public interface ControllerConsts {

	public static final String PRODUCES = "application/json; charset=utf-8";
	
	public static final String WEB_CONTROLLER_URL = "web/controller";

	public static final String WEB_PUBLIC_CONTROLLER_URL = WEB_CONTROLLER_URL + "/public";

	public static final String WEB_AUTHENTICATED_CONTROLLER_URL = WEB_CONTROLLER_URL + "/authenticated";

	public static final String LOGIN_PAGE = "login";
	public static final String INDEX_PAGE = "index";
	public static final String USER_PROFILE_PAGE = "index#my-profile";

	/** Authentication Controller START **/
	public static final String LOGIN = "/login";
	public static final String LOGOUT = "/logout";
	public static final String DO_LOGIN = "/doLogin";
	public static final String EXPIRED_SESSION = "/expiredSession";
	public static final String CHECK_EXPIRED_SESSION = "/checkSessionExpired";
	/** Authentication Controller END **/
	//user
	public static final String USERS_GET_BASIC_INFO_SESSION = WEB_AUTHENTICATED_CONTROLLER_URL + "/basic-info-session";
	public static final String USERS_LIST_USER_DATA_MODEL = WEB_AUTHENTICATED_CONTROLLER_URL + "/user-list-data-model";
	public static final String USERS_PROFILE_DATA_ID=WEB_AUTHENTICATED_CONTROLLER_URL+"/user-profile-id";
	
	
	
	
	
	
	
	// action log module start
	public static final String ACTION_LOGS = WEB_AUTHENTICATED_CONTROLLER_URL + "/action-logs";
	public static final String ACTION_LOGS_LIST_ACTION_LOG_DATA_MODEL = ACTION_LOGS + "/list-action-log-data-model";
	// action log module end
	
	/* for common controller */
	public static final String COMMON = WEB_AUTHENTICATED_CONTROLLER_URL + "/common";
	
	/* for management city controller */
	public static final String COURSE = WEB_AUTHENTICATED_CONTROLLER_URL + "/course";
	public static final String COURSE_CREATE = COURSE;
	public static final String COURSE_GET = COURSE + "/{course-id}";
	public static final String COURSE_UPDATE = COURSE + "/{course-id}";
	public static final String COURSE_DELETE = COURSE + "/{course-id}";
	public static final String COURSE_LIST = COURSE;
	public static final String COURSE_LIST_FILTER = COURSE + "/filter";
	/* end management city controller */
	
	
	  
	 
}
