
package com.direction.api.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.modelmapper.ModelMapper;

import com.direction.api.service.IBasicService;
import com.direction.common.dao.IPlaceDAO;
import com.direction.common.dao.IUserDAO;
import com.direction.common.factory.IDAOFactory;
import com.direction.common.factory.impl.DAOFactory;
import com.direction.common.hibernate.HibernateUtil;
import com.direction.common.util.CommonUtil;
import com.direction.common.util.SingletonFactory;
import com.direction.common.util.Util;
import com.google.gson.Gson;

public abstract class AbstractBasicService implements IBasicService {

	private static final Logger log = Logger.getLogger(AbstractBasicService.class);
	
	protected HibernateUtil hibernate = HibernateUtil.getInstance();
	
	protected Gson gson = SingletonFactory.getGsonInstance();
	protected ModelMapper mapper = SingletonFactory.getModelMapperInstance();
	protected IDAOFactory daoFactory = DAOFactory.getInstance();	
	protected IUserDAO userDAO = DAOFactory.getInstance().createUserDao();
	protected IPlaceDAO placeDAO = daoFactory.createPlaceDao();
	
	
	
	protected Session openSession() {
		return HibernateUtil.getInstance().openSession();
	}
	protected void safeCloseSession(Session session) {
		HibernateUtil.getInstance().safeCloseSession(session);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	@Override
	public <E> List<E> selectForDTO(String sql, Map<String, Object> conditions, Class<E> clazz) {
		List<E> list = null;
		Session session = openSession();
		try {
			NativeQuery nativeQuery = session.createNativeQuery(sql);
			if (conditions != null && !conditions.isEmpty()) {
				conditions.forEach((key, value) -> {
					nativeQuery.setParameter(key, value);
				});
			}
			nativeQuery.unwrap(org.hibernate.query.Query.class);
			nativeQuery.setResultTransformer(Transformers.aliasToBean(clazz));
			list = nativeQuery.getResultList();
		} catch (Exception e) {
			log.error(CommonUtil.convertExceptionToString(e));
		} finally {
			safeCloseSession(session);
		}
		return list;
	}
	@Override
	public int count(String sql) {
		int count = 0;
		Session session = hibernate.openSession();
		try {
			String countSQL = "select count(1) from (" + sql + ") temp";
			SQLQuery query = session.createSQLQuery(countSQL);
			query.setCacheable(false);
			count = ((BigInteger) query.uniqueResult()).intValue();
		} catch (Exception e) {
			log.error(Util.convertExceptionToString(e));
		} finally {
			hibernate.safeCloseSession(session);
		}
		return count;
	}
	
	@Override
	public int count(String sql, Map<String, Object> conditions) {
		int count = 0;
		Session session = hibernate.openSession();
		try {
			//String countSQL = "select count(1) from (" + sql + ") temp";
			String countSQL=sql;
			SQLQuery query = session.createSQLQuery(countSQL);
			query.setCacheable(false);
			if (conditions != null && !conditions.isEmpty()) {
				conditions.forEach((key, value) -> {
					query.setParameter(key, value);
				});
			}
			count = ((BigInteger) query.uniqueResult()).intValue();
		} catch (Exception e) {
			log.error(Util.convertExceptionToString(e));
		} finally {
			hibernate.safeCloseSession(session);
		}
		return count;
	}
	
	@Override
	public Integer tryParseInteger(String text){
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException e) {
			return -1;
		}
	}
	@Override
	public int execute(String sql, Map<String, Object> conditions) {
		int result = -1;
		Session session = hibernate.openSession();
		try {
			String countSQL = sql;
//			log.debug(sql);
			Transaction transaction = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(countSQL);
			query.setCacheable(false);
			if (conditions != null && !conditions.isEmpty()) {
				conditions.forEach((key, value) -> {
					query.setParameter(key, value);
				});
			}
			result = query.executeUpdate();
			transaction.commit();
			//query.uniqueResult();
		} catch (Exception e) {
			log.error(Util.convertExceptionToString(e));
		} finally {
			hibernate.safeCloseSession(session);
		}
		return result;
	}
	
}
