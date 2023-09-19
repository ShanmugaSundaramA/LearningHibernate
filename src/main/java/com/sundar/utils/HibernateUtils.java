package com.sundar.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

	static ThreadLocal<Session> threadLocal = new ThreadLocal<>();
	static SessionFactory sessionFactory = null;

	static {
		System.out.println("Inside the static block");
		sessionFactory = new Configuration().configure("/com/sundar/config/hibernate.cfg.xml").buildSessionFactory();
	}

	public static Session getSession() {
    	 Session session = null;
    	 if(threadLocal.get() != null) {
    		 session = threadLocal.get();
    	 }else {
    	     session = sessionFactory.openSession();
    	     threadLocal.set(session);
    	 }    	 
    	 return session;
     }
	
	public static void closeSession() {
		if(threadLocal.get() != null ) {
			Session session = threadLocal.get();
			session.close();
			threadLocal.remove();
		}
	}
	
	public static void closeSessionFactory() {
		sessionFactory.close();
	}
}
