package com.direction.api.bo;
//package com.golfer.api.bo;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Service;
//
//
//import com.golfer.consts.GlobalConsts;
//import com.golfer.dao.IDAOFactory;
//import com.golfer.dao.impl.DAOFactory;
//com.golfer.common.dao.entity.UserEntity;
//import com.golfer.service.IUserService;
//import com.golfer.util.ObjectUtil;
//import com.golfer.web.authentication.SessionManager;
//import com.golfer.web.model.BasicInfoSession;
//import com.golfer.web.model.UserModel;
//import com.google.gson.Gson;
//
//@Service
//public class UserBO {
//	private static final Logger log =Logger.getLogger(UserBO.class);
//	
//	private Gson gs=ObjectUtil.getGsonInstance();
//	@Autowired
//	private IUserService userService;
//	
//	@Autowired
//	private SessionManager sessionManager;
//	
//	public BasicInfoSession getBasicInfoSession(){
//		System.out.println("vao day 2");
//		BasicInfoSession basicInfossion =new BasicInfoSession();
//		
//		
//		UserEntity user= this.userService.getUserByUserId(this.sessionManager.getUserId());
//		user.setPassword("");
//		Map<String, Integer> permissions =this.userService.getPermissions(user.getUsername());
//		basicInfossion.setUser(user);
//		basicInfossion.setPermissions(permissions);
//		basicInfossion.setDefaultPassword(GlobalConsts.USER_DEFAULT_PASSWORD);
//		basicInfossion.setLanguage(sessionManager.getUserInfoSession().getLanguage());
//		
//		
//		IDAOFactory daoFactory=DAOFactory.getInstance();
//		
//		return basicInfossion;
//	}
//	//@PreAuthorize("hasPermission(principal, '" + com.golfer.web.authentication.RolePermissionsMapping.ROLE_USER + "')")
//	public UserModel getListUserDataModel(){
//		
//		//System.out.println("vao get list 2");
//		UserModel userModelEntity=new UserModel();
//		
//		Integer currentUserId=sessionManager.getUserId();
//		
//		List<UserEntity>temp=this.userService.getListUser();
//		//System.out.println("sdsd"+temp.size());
//		List<UserEntity>users=new ArrayList<UserEntity>();
//		for(UserEntity user:temp){
//
//			
//
//			if(currentUserId!=user.getId()&& !GlobalConsts.DEFAULT_SUPERADMIN_USERNAME.equals(user.getUsername())){
//				//System.out.println("vao add");
//
//				users.add(user);
//			}
//		}
//		userModelEntity.setUsers(users);
//		//System.out.println("so pt"+userModelEntity.getUsers().size());
//		return userModelEntity;
//		
//	}
//
//}
