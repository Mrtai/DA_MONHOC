package com.direction.api.authen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.direction.api.consts.RequestMappingConsts;
import com.direction.api.service.impl.AbstractBasicService;
import com.direction.common.entity.UserEntity;
import com.direction.common.util.CommonUtil;

import com.google.gson.Gson;

public class Authentication extends AbstractBasicService implements Filter {
	
	private Gson gs = new Gson();
	private static final Logger log = Logger.getLogger(Authentication.class);
	private static final String AUTHORIZATION_BEARER = "Bearer ";
	private static final String AUTHORIZATION_HEADER = "Authorization";

//	 @Autowired
//	private ILoginService loginService;
	 
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//log.info("init is called");
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		
		
		String authorization = request.getHeader(AUTHORIZATION_HEADER);
		String url = request.getPathInfo();
		String token = "";

		if (authorization != null && !authorization.isEmpty() && authorization.length() > AUTHORIZATION_BEARER.length()) {
			
			token = authorization.substring(AUTHORIZATION_BEARER.length());
			//token = authorization;
		}
		StringBuilder logs = new StringBuilder();
		logs.append("Content-Type: " + request.getContentType()).append("\n");
		logs.append("Method: " + request.getMethod()).append("\n");
		logs.append("URL: " + request.getPathInfo()).append("\n");
		logs.append("QueryString: " + request.getQueryString()).append("\n");
		logs.append("RemoteAddr: " + request.getRemoteAddr()).append("\n");
		logs.append("RequestURI: " + request.getRequestURI()).append("\n");
		logs.append("RequestURL: " + request.getRequestURL()).append("\n");
		logs.append("Authorization: " + request.getHeader(AUTHORIZATION_HEADER)).append("\n");
		logs.append("Parameters: " + this.gs.toJson(request.getParameterMap())).append("");
		log.info(logs);
		
		try {
//			boolean result = true;
			if (url.equals(RequestMappingConsts.LOGIN)
					|| url.equals(RequestMappingConsts.USER_ADD)
					|| userDAO.checkTokenValid(token)) {
				
				
				if(userDAO.checkTokenValid(token)){
					req.setAttribute("check", true);
					try {
						
						UserEntity us = userDAO.getUserInfoByToken(token);
										
						if(us==null){							
							req.setAttribute("check", false);
						}else{
							// check accsettoken paremeter for usser set active onmessage when connect websocket
//							
							req.setAttribute("user-id", us.getId());
							req.setAttribute("token", token);
						}					
						
					} catch (Exception e) {
						log.error(CommonUtil.convertExceptionToString(e));
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
						return;
					}
				}
				filterChain.doFilter(req, res);	
			} else {
				
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
				return;
			}
		} catch (Exception ex) {
			log.error(CommonUtil.convertExceptionToString(ex));
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
			return;
		}
	}

	@Override
	public void destroy() {
		log.info("destroy is called");
	}
}