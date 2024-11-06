package de.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.app.entities.Address;
import de.app.entities.Communication;
import de.app.entities.Person;
import de.app.entities.Profile;
import de.app.utils.HibernateUtilMySql;
import de.app.utils.IHibernateUtil;

@SuppressWarnings("unused")
public class PersonDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(PersonDao.class);
	private static PersonDao instance;
	private static IHibernateUtil HibernateUtil = HibernateUtilMySql.getInstance();

	private PersonDao() {}
	
	public static PersonDao getInstance() {
	    if(PersonDao.instance == null) {
	    	PersonDao.instance = new PersonDao();
	    }	   
	    return PersonDao.instance;
	}
	
	public static PersonDao getInstance(IHibernateUtil hibernateUtil) {	    
	    PersonDao.HibernateUtil = hibernateUtil;
	    return getInstance();
	}
	
	public void setDefaultConnection(IHibernateUtil hibernateUtil) {
		HibernateUtil = hibernateUtil;
	}
	
	public Person save(Person person) {
		Session session = null;
		Transaction transaction = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            Person _person = person.getId() == null ? null : session.find(Person.class, person.getId());
            if(_person == null) 
            	session.persist(person); 
            else {
            	Profile profile = session.find(Profile.class, person.getId());
            	profile.update(person.getProfile());
            	
            	Communication communication = session.find(Communication.class, person.getId());
            	communication.update(person.getCommunication());
            	
            	Address address = session.find(Address.class, person.getId());
            	address.update(person.getAddress());
            	
            	person.setProfile(profile);
            	person.setCommunication(communication);
            	person.setAddress(address);
            	         	
            	_person.update(person);
            }
            
            transaction.commit();
            return person;           
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("PersonDao save: " + e.getMessage());
            return null;          
        } finally {
			 session.close();
		}
    }
    
    public void delete(Person person) {
    	Session session = null;
		Transaction transaction = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
        	transaction = session.beginTransaction();
        	
        	/*MutationQuery query = session.createMutationQuery("DELETE Timesheet WHERE person = :person");    		   			
    		query.setParameter("person", person);
    		query.executeUpdate();
    		
    		query = session.createMutationQuery("DELETE ContractCondition WHERE person = :person");    		   			
    		query.setParameter("person", person);
    		query.executeUpdate();
    		
    		query = session.createMutationQuery("DELETE Vacation WHERE person = :person");    		   			
    		query.setParameter("person", person);
    		query.executeUpdate();
    		
    		query = session.createMutationQuery("DELETE Profile WHERE id = :person");    		   			
    		query.setParameter("person", person);
    		query.executeUpdate();
    		
    		query = session.createMutationQuery("DELETE Communication WHERE id = :person");    		   			
    		query.setParameter("person", person);
    		query.executeUpdate();
    		
    		query = session.createMutationQuery("DELETE Address WHERE id = :person");    		   			
    		query.setParameter("person", person);
    		query.executeUpdate();
    		  		
    		NativeQuery<Object> nativeQuery = session.createNativeQuery("DELETE FROM PROJECT_PERSON WHERE PERSON_ID = '" + person.getId() + "'", Object.class);
    		nativeQuery.executeUpdate();*/
        	
    		session.remove(person);
                     
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }           
            LOG.error("PersonDao delete: " + e.getMessage());
        } finally {
        	session.close();
		}
    }
    
    public void deleteAll() {
    	Session session = null;
		Transaction transaction = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createMutationQuery("DELETE FROM Person").executeUpdate(); 
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }           
            LOG.error("PersonDao deleteAll: " + e.getMessage());
        } finally {
        	session.close();
		}
    }
    
    public List<Person> findAll() {
    	List<Person> list = null;
    	Session session = null;
    	try {   	
    		session = HibernateUtil.getSessionFactory().openSession();
    		list = session.createQuery("SELECT person FROM Person AS person", Person.class).getResultList(); 
    	}catch (Exception e) {	 
    		 LOG.error("PersonDao findAll: " + e.getMessage());
        } finally {
        	session.close();
		}
    	return list;      
    }
    
    public Person findById(String id){
    	Person person = null;  
    	Session session = null;
    	try  {
    		session = HibernateUtil.getSessionFactory().openSession();
            person = session.get(Person.class, id);
        } catch (Exception e) {
        	LOG.error("PersonDao findById: " + e.getMessage());
        } finally {
        	session.close();
		}	
    	return person;
    }
    
    public Person findByPasswordResetToken(String passwordResetToken){
    	Person person = null;
    	Session session = null;
    	try {   
    		session = HibernateUtil.getSessionFactory().openSession();
    		Query<Person> query = session.createQuery("SELECT person FROM Person AS person WHERE person.passwordResetToken = :passwordResetToken", Person.class);    		   			
    		query.setParameter("passwordResetToken", passwordResetToken);
    		query.setFirstResult(0);
    		query.setMaxResults(1); 
    		person = query.uniqueResult();
    		
    		session.close();
    	} catch (Exception e) {
        	LOG.error("PersonDao passwortResetToken: " + e.getMessage());
        } finally {
        	session.close();
		}		
    	return person;
    }
}
