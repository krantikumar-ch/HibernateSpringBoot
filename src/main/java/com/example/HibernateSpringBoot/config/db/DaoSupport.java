/**
 * 
 */
package com.example.HibernateSpringBoot.config.db;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

/**
 * @author suganya
 *
 */

public abstract class DaoSupport extends HibernateDaoSupport{
	
	@Autowired    
  	public final void setSessionFacotry(SessionFactory sessionFacotry) {
		System.out.println("sessionfactory class "+sessionFacotry.getClass().getName());
       super.setSessionFactory(sessionFacotry);   
  	}  

}
