package de.app.utils;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import de.app.entities.Address;
import de.app.entities.Communication;
import de.app.entities.ContractCondition;
import de.app.entities.Person;
import de.app.entities.Profile;
import de.app.entities.Project;
import de.app.entities.TechnicalKey;
import de.app.entities.Timesheet;
import de.app.entities.Vacation;

public class HibernateUtilMySql implements IHibernateUtil {
	
	private SessionFactory sessionFactory = null;	
	private static HibernateUtilMySql instance;
	
	private HibernateUtilMySql() {}
	 
	public static HibernateUtilMySql getInstance () {
	    if (HibernateUtilMySql.instance == null) {
	    	HibernateUtilMySql.instance = new HibernateUtilMySql();
	    }
	    return HibernateUtilMySql.instance;
	}
	
	public void createSessionFactory(String url, String username, String password) {
		if (sessionFactory == null) {
		      Configuration configuration = new Configuration();
		         
		      Properties settings = new Properties();
		        
		      settings.put(Environment.JAKARTA_JDBC_URL, url);
		      settings.put(Environment.JAKARTA_JDBC_USER, username);
		      settings.put(Environment.JAKARTA_JDBC_PASSWORD, password);
		      settings.put(Environment.JAKARTA_JDBC_DRIVER, "com.mysql.cj.jdbc.Driver");
		      //settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
		      settings.put(Environment.POOL_SIZE, 20);
		      settings.put("hibernate.connection.min_pool_size", 5);
		      settings.put("hibernate.connection.autocommit", false);
		      settings.put(Environment.SHOW_SQL, false);
		      settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
		      settings.put(Environment.HBM2DDL_AUTO, "update");
		      settings.put("hibernate.c3p0.min_size", 5);
		      settings.put("hibernate.c3p0.max_size", 20);
		      settings.put("hibernate.c3p0.acquire_increment", 5);
		      settings.put("hibernate.c3p0.timeout", 1800);
		      
		      settings.put("hibernate.c3p0.testConnectionOnCheckout", true);
		      settings.put("hibernate.c3p0.validate", true);
		     

		      configuration.setProperties(settings);		
		      configuration.addAnnotatedClass(Person.class);
		      configuration.addAnnotatedClass(TechnicalKey.class);              
		      configuration.addAnnotatedClass(Timesheet.class);
		      configuration.addAnnotatedClass(Vacation.class);
		      configuration.addAnnotatedClass(ContractCondition.class);
		      configuration.addAnnotatedClass(Profile.class);
		      configuration.addAnnotatedClass(Address.class);
		      configuration.addAnnotatedClass(Communication.class);
		      configuration.addAnnotatedClass(Project.class);
		      
		      ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
		              .applySettings(configuration.getProperties()).build();

		      sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		      setForeignKeys();
	    }
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	private void setForeignKeys() {
    	
    	Session session = null;
		Transaction transaction = null;
    	try {  	  		
    		session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createNativeQuery("ALTER TABLE contract_condition DROP FOREIGN KEY fk_person_contract_condition", Object.class).executeUpdate();
            session.createNativeQuery("ALTER TABLE contract_condition ADD CONSTRAINT fk_person_contract_condition FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE SET NULL", Object.class).executeUpdate();

            session.createNativeQuery("ALTER TABLE timesheet DROP FOREIGN KEY fk_person_timesheet", Object.class).executeUpdate();
            session.createNativeQuery("ALTER TABLE timesheet ADD CONSTRAINT fk_person_timesheet FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE SET NULL", Object.class).executeUpdate();

            session.createNativeQuery("ALTER TABLE vacation DROP FOREIGN KEY fk_person_vacation", Object.class).executeUpdate();
            session.createNativeQuery("ALTER TABLE vacation ADD CONSTRAINT fk_person_vacation FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE SET NULL", Object.class).executeUpdate();

            session.createNativeQuery("ALTER TABLE timesheet DROP FOREIGN KEY fk_project_timesheet", Object.class).executeUpdate();
            session.createNativeQuery("ALTER TABLE timesheet ADD CONSTRAINT fk_project_timesheet FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE SET NULL", Object.class).executeUpdate();
            
            session.createNativeQuery("ALTER TABLE project_person DROP FOREIGN KEY fk_project_person", Object.class).executeUpdate();
            session.createNativeQuery("ALTER TABLE project_person ADD CONSTRAINT fk_project_person FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE", Object.class).executeUpdate();
           
            session.createNativeQuery("ALTER TABLE project_person DROP FOREIGN KEY fk_person_person", Object.class).executeUpdate();
            session.createNativeQuery("ALTER TABLE project_person ADD CONSTRAINT fk_person_person FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE", Object.class).executeUpdate();
                      
            transaction.commit();
    	}catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }           
        }finally {
        	session.close();
		}
    }   
    
}
