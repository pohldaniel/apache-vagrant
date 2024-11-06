package de.app.utils;

import org.hibernate.SessionFactory;

public interface IHibernateUtil {
	public void createSessionFactory(String url, String username, String password);
	  public SessionFactory getSessionFactory();
}
