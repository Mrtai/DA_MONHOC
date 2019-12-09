package com.direction.api.consts;

public interface GlobalConsts {

        public static final String DEFAULT_SUPERADMIN_USERNAME 	= "superadmin";
        
	    public static final String DEFAULT_GROUP_SUPERADMIN 	= "group-superadmin";
	    
	    public static final String URL_RESETPASSWORD			= "http://14.225.5.44:8080/golfer_webapp/forgot";
	    
	    //public static final String ACCESS_TOKEN_FB              = "EAAKIx7bu1b4BAGtRGUjFhuRBIO6OQeNEzXfFFCFuZAZANageUeFfreMdvIjZCivyOM8ZAeU6oXlmVfSefLZBGQz0PutioEYNgx6JA5muwEF80rgChB5irB9rTBKbLWTyN0GZAU3ztGsThnMR2OHHR1Eto6zPRDrsTLCknP03ZAHy8QrD1ypL7O1NLCdlV6AuVgZD";
	    public static final String FACEBOOK_APP_ID				=  "503331813804161";//"713341302396350";
	    public static final String FACEBOOK_APP_SECRET			= "0c16662aace082808a0436693922d4c3";//"4e89e8a32f17d063890b7bcae0926e7c";
//	    public static final String FACEBOOK_APP_ID				=  "713341302396350";
//	    public static final String FACEBOOK_APP_SECRET			= "4e89e8a32f17d063890b7bcae0926e7c";
	    public static final String KEY_DIRECTION                = "AIzaSyA6cDU_pln3LpvcypA3AENSDoxP3lfCYGA";
	    
	    public static final String DEFAULT_DATE_FORMAT			= "MM/dd/yyyy";
	    public static final String DEFAULT_DATE_TIME_FORMAT 	= "MM/dd/yyyy hh:mm:ss";
	    public static final String DEFAULT_TIME_FORMAT 			= "hh:mm:ss";

	    public static final int MAX_RECORD 						= 2000;

	    public final static String J_USERNAME 					= "j_username";
	    public final static String J_PASSWORD 					= "j_password";
	    public final static String AJAX_HEADER 					= "XMLHttpRequest";
	    public final static String AJAX_HEADER_ATTRIBUTE 		= "X-Requested-With";
	    
	    public final static String STATUS_ACTIVED 				= "ACT";
	    
	    //for favorite 
	    public final static String STATUS_FAVORITE 				= "FAVORITE";
	    public final static String STATUS_FRIEND 				= "FRIEND";
	    
	    public final static String STATUS_LOCKED  				= "LCK";
	    public final static String STATUS_DELETED 				= "DEL";

	    public final static int Match_first_win=1;
	    public final static int Match_second_wind=2;
	    public final static int Match_draw=0;
	    
	    public final static String STATUS_NEW 				    = "NEW";
	    
	    //Active
	    public final static String ACT_ONLINE					= "ONLINE";
	    public final static String ACT_OFFLINE 					= "OFFLINE";
	    public final static String ACT_ONMESSAGE 				= "ONMESSAGE";
	    //=============================

	    public final static String USER_DEFAULT_PASSWORD 		= "abc@123";
	    public final static String USER_COOKIE 					= "user_cookie";
	    public final static String USER_COOKIE_USERNAME 		= "user_cookie_username";
	    public final static String USER_COOKIE_PASSWORD 		= "user_cookie_password";
	    public final static String USER_COOKIE_PATH 			= "/";
	    public final static String CROUS_LANGUAGE_COOKIE_PATH 	= "/";
	    
	    public final static int USER_COOKIE_EXPIRED 			= 7*24*60*60;
	    
	    public static final int ACTION_LOG_TTL_DEFAULT 			= 180*24*60*60;
	    
	    public static final String DEFAULT_TIMEZONE 			= "GMT+07";
	    
	    public static final int PAGINATION_NUMBER_DEFAULT 		= 10;
	    
	    
	    // table name start
	    public static final String TABLE_ACTION_LOG 					= "action_log";
	    public static final String TABLE_USER 							= "user";
	    public static final String TABLE_COUNTRY 						= "country";
	    public static final String TABLE_FORMULE 						= "formule";
	    public static final String TABLE_FRIEND 						= "relationship_friend";
	    public static final String TABLE_GOLF_COURSE 					= "golf_course";
	    public static final String TABLE_IMAGE 							= "images";
	    public static final String TABLE_INVITE 						= "request_friend";
	    public static final String TABLE_MEMBER 						= "members";
	    public static final String TABLE_MESSAGE 						= "messages";
	    public static final String TABLE_NOTIFICATION 					= "notification";
	    public static final String TABLE_CHALLENGE						= "challenge";
	    public static final String TABLE_PROFILE 						= "profile";
	    public static final String TABLE_SETTING						= "setting";
	    public static final String TABLE_RANKING 						= "ranking";
	    public static final String TABLE_REGION			 				= "region";
	    public static final String TABLE_ROOM 							= "room";
	    public static final String TABLE_RULE 							= "rules";
	    public static final String TABLE_CONVERSATION 					= "conversation";
	    public static final String TABLE_MESSAGE_CONVERSATION 			= "message_conversation";
	    public static final String TABLE_SCHEDULE 						= "schedule";
	    public static final String TABLE_LANGUAGE_TYPE					= "language_type";
	    public static final String TABLE_LANGUAGE_CODE					= "language_code";
	    public static final String TABLE_LANGUAGE_KEY					= "language_key";
	    public static final String TABLE_LANGUAGE						= "language";
	    public static final String TABLE_GOLF_COURSE_HOLE				= "golf_course_hole";
	    public static final String TABLE_RESULT_DETAIL					= "result_detail";
	    public static final String TABLE_ADVERSEMENT					= "advertisement";
	    public static final String TABLE_GROUP_USER						= "group_user";
	    public static final String TABLE_M_GROUP_HAS_FUNCTION			= "group_has_function";
	    public static final String TABLE_M_FUNCTION						= "function";
	    // table name end
	    
	    public static final String ACTION_LOG_ADD 							= "ADD";
	    public static final String ACTION_LOG_UPDATE 						= "UPDATE";
	    public static final String ACTION_LOG_DELETE 						= "DELETE";
	    
	    public static final String ACTION_LOG_FUNCTION_USER 				= "USER";
	    
	    public static final String ACTION_LOG_STATUS_FAIL 					= "FAIL";
	    public static final String ACTION_LOG_STATUS_SUCCESS 				= "SUCCESS";
	    
	    public static final String SERVICE_TYPE_REQ 						= "request";
	    public static final String SERVICE_TYPE_RESP 						= "response";
	    
	    public static final String SERVICE_METHOD_GET 						= "GET";
	    public static final String SERVICE_METHOD_POST 						= "POST";
	    public static final String SERVICE_METHOD_PUT 						= "PUT";
	    public static final String SERVICE_METHOD_DELETE 					= "DELETE";
	    
	    
	    // hieu.lt add const status return
	    public static final String STATUS_OK 					= "Success";
	    public static final String STATUS_FAIL 					= "Fail";
	    
	    public static final Integer MESSAGE_NEW					= 0;
	    public static final Integer MESSAGE_ACTIVE 				= 1;
	    
	    
	    
}
