package com.direction.common.hibernate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.reflections.Reflections;

import com.direction.common.util.CommonUtil;


public class HibernateUtil {

	private static final Logger log = Logger.getLogger(HibernateUtil.class);

	private static HibernateUtil instance = null;

	private SessionFactory sessionFactory = buildSessionFactory();

	private HibernateUtil() {
	}

	public static HibernateUtil getInstance() {
		try {
			if (instance == null) {
				instance = new HibernateUtil();
			}
		} catch (Exception ex) {
			log.error(CommonUtil.convertExceptionToString(ex));
		}
		return instance;
	}

	public SessionFactory buildSessionFactory() {
		SessionFactory sessionFactory = null;
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		Reflections reflections = new Reflections("com.direction.common.entity");
		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(javax.persistence.Entity.class);
		for (Class<?> clazz : classes) {
			log.debug(clazz.getName());
			configuration.addAnnotatedClass(clazz);
		}

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		log.info("Hibernate just has been started up successfully");
		return sessionFactory;
	}

	public void closeSessionFacotry() {
		if (!this.sessionFactory.isClosed()) {
			this.sessionFactory.close();
		}
	}

	/* sets and gets method */

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public void safeCloseSession(Session session) {
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	public Session openSession() {
		return sessionFactory.openSession();
	}
	
	public Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

}
