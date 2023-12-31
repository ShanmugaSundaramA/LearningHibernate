package com.sundar;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sundar.domain.Student;
import com.sundar.utils.HibernateUtils;

public class App {
	public static void main(String[] args) {

		Transaction tx = null;
		Session session = HibernateUtils.getSession();

		/*
		 * For Employee Table
		 * 
		Employee emp = new Employee();
		emp.setId(2);
		emp.setFirstName("Sundar");
		emp.setLastName("Arumugam");
		emp.setEmail("sundarvikram08@gmail.com");
		*
		*/
		
		Student student = new Student();
		student.setId(1);
		student.setName("sundar");
		student.setEmail("sundar08@gmail.com");
		
		try {

			tx = session.beginTransaction();
			int save = (int) session.save(student);
			tx.commit();
			System.out.println("save " + save);

		} catch (Exception e) {

			tx.rollback();
			System.out.println(e + " error....");

		} finally {

			HibernateUtils.closeSession();
			HibernateUtils.closeSessionFactory();
		}

	}
}
