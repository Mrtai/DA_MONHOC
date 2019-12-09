package com.direction.api.service;

import java.util.List;
import java.util.Map;


public interface IBasicService {
	
	<E> List<E> selectForDTO(String sql, Map<String, Object> conditions, Class<E> clazz);
	
	int count(String sql);
	
	int count(String sql, Map<String, Object> conditions);
	int execute(String sql, Map<String, Object> conditions);
	
	Integer tryParseInteger(String text);
}
