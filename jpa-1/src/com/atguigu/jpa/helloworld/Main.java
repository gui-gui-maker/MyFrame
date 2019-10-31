package com.atguigu.jpa.helloworld;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
	
	public static void main(String[] args) {
		
		//1. ���� EntitymanagerFactory
		String persistenceUnitName = "jpa-1";
		
		Map<String, Object> properites = new HashMap<String, Object>();
		properites.put("hibernate.show_sql", true);
		
		EntityManagerFactory entityManagerFactory = 
				//Persistence.createEntityManagerFactory(persistenceUnitName);
				Persistence.createEntityManagerFactory(persistenceUnitName, properites);
				
		//2. ���� EntityManager. ������ Hibernate �� SessionFactory
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//3. ��������
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		//4. ���г־û�����
		Customer customer = new Customer();
		customer.setAge(12);
		customer.setEmail("tom@atguigu.com");
		customer.setLastName("Tom");
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		
		entityManager.persist(customer);
		
		//5. �ύ����
		transaction.commit();
		
		//6. �ر� EntityManager
		entityManager.close();
		
		//7. �ر� EntityManagerFactory
		entityManagerFactory.close();
	}
	
}
