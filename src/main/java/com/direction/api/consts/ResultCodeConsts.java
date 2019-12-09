package com.direction.api.consts;

public enum ResultCodeConsts {

	SUCCESS						(0, "Success"),
	DATA_NOT_FOUND				(1, "Data not found"),
	
	PARAMETER_INVALID			(2, "Parameter invalid"),
	USERNAME_OR_EMAIL_INVALID	(3, "Username or email is invalid"),
	PASSWORD_INVALID			(4, "Password is invalid"),
	ERROR_CHANGE_PASSWORD		(5, "Cannot change password"),
	ERROR_NEW_PASSWORD_MATCH_PREVIOUS_PASSWORD	(6, "New password must not match previous password"),
	ERROR_NEW_PASSWORD_MATCH_CURRENTLY_PASSWORD	(7, "New password must not match currently password"),
	CANNOT_UPDATE				(8, "Cannot update data"),
	CANNOT_CREATE				(9, "Cannot create data"),
	CANNOT_DELETE				(10, "Cannot delete data"),
	USER_LOCKED					(11, "User is locked"),
	FACEBOOKID_INVALID			(12, "Facebooke id is invalid "),
	GOOGLEID_INVALID			(13, "Google id is invalid"),
	FACEBOOK_TOKEN_INVALID		(14, "Facebook token is invalid"),
	USER_NOT_EXIST				(902, "User does not exist, please register!"),
	GOOGLE_TOKEN_INVALID		(15, "Google token is invalid"),
	ERROR_NEW_USER				(16, "error new user"),
	ERROR_URL					(900, "error get url"),
	ERROR_GET_ACCESS_FACEBOOK   (901, "error get access facebook"),
	ERROR_GET_ACCESS_DIRECTION   (902, "error get access api"),
	MATCH_ALREADY_EXISTS		(996, "The match already exists"),
	RESUILT_ALREADY_EXISTS		(997, "The result already exists"),
	SYSTEM_ERROR				(999, "System error"),
	DATA_NOT_FOUND_INPUT				(998, "Data not found input"),
	ALREADY_EXIST_TO 			(18,"invite already exit on to "),
	ALREADY_EXIST_FROM   (17,"invite already exit on from"),
	ALREADY_LOCATION		(27,"Alredy location"),
	SCHEDULE_NEW 		(19,"schedule in new add "),
	SCHEDULE_DONE		(20,"Match completed"),
	ROOM_NEW		(23,"Room new "),
	ROOM_SINGLE 		(25,"Room two people "),
	ROOM_GROUP		(24,"Room many people "),
	MEMBER_NEW		(26,"Memer new"),
	DATABASE_ERROR	(1000,"database eror"),
	NEW_CONVERSATION		(11,"conversation new"),
	ERROR_USER_ID		(995," you not role  "),
	LOTTERY_ILLEGAL     (992, "Lottery is illegal"),
	LOTTERY_USER_TICKET_ISEXIST(993,"You have run out of opportunities"),
	
	;
	private final Integer code;
	private final String message;

	private ResultCodeConsts(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public static String getMessageByCode(Integer code) {
		for (ResultCodeConsts type : values()) {
			if (type.getCode().equals(code)) {
				return type.getMessage();
			}
		}
		return "";
	}
	
}