package com.direction.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

public interface IBasicDAO<T, K extends Serializable> {

	T findById(K id);

	List<T> findAll(int size, String... colsRet);
	
	List<T> findAllOrderBy(int size, String orderByName, String order, String... colsRet);

	boolean remove(K id);

	boolean persist(T t);

	boolean update(T t);

	boolean exists(K id);

	List<T> findBy(String colName, Object colValue, String... colsRet);

	T getUniqueBy(String colName, Object colValue, String... colsRet);

	List<T> findBy(Map<String, Object> cols, String... colsRet);

	T getUniqueBy(Map<String, Object> cols, String... colsRet);

	boolean exist(String colName, Object colValue);

	boolean exist(Map<String, Object> cols);

	boolean update(Map<String, Object> cols, String pk, Object val);

	int getLast(String fieldName);

	T getFirstEntityByConditions(Map<String, Object> conditions, Class<T> clazz);

	List<T> getEntityByConditions(Map<String, Object> conditions, Class<T> clazz);
	
	T getFirstEntityByConditionsNull(Map<String, Object> conditions, Class<T> clazz);

	List<T> getEntityByConditionsNull(Map<String, Object> conditions, Class<T> clazz);
	
	T getFirstEntityByConditions(Map<String, Object> conditions,String col,String orderBy, Class<T> clazz);
	
	List<T> getEntityByConditions(Map<String, Object> conditions,String col,String orderBy, Class<T> clazz);

	boolean isExisted(K id, Session session) throws Exception;

	boolean isExisted(K id);

	T deleteById(K id, Session session) throws Exception;

	T deleteById(K id);

	T delete(T entity, Session session) throws Exception;

	T delete(T entity);

	T update(T entity, Session session) throws Exception;

	T create(T entity, Session session) throws Exception;

	T create(T entity);

	T saveOrUpdate(T entity, Session session) throws Exception;

	T saveOrUpdate(T entity);

	T findById(K id, Session session) throws Exception;
	
	int count(String tableName);
}
