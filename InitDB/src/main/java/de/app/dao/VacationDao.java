package de.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.app.entities.Person;
import de.app.entities.Vacation;
import de.app.utils.HibernateUtilMySql;
import de.app.utils.IHibernateUtil;

public class VacationDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(VacationDao.class);
	private static VacationDao instance;
	private static IHibernateUtil HibernateUtil = HibernateUtilMySql.getInstance();

	private VacationDao() {}
	 
	public static VacationDao getInstance() {
	    if(VacationDao.instance == null) {
	    	VacationDao.instance = new VacationDao();
	    }	   
	    return VacationDao.instance;
	}
	
	public static VacationDao getInstance(IHibernateUtil hibernateUtil) {
	    VacationDao.HibernateUtil = hibernateUtil;
	    return getInstance();
	}
	
	public void setDefaultConnection(IHibernateUtil hibernateUtil) {
		HibernateUtil = hibernateUtil;
	}
	
	public Vacation save(Vacation vacation) {
		Session session = null;
		Transaction transaction = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            Vacation _vacation = vacation.getId() == null ? null : session.find(Vacation.class, vacation.getId());
            if(_vacation == null) {
            	vacation.updateVacationDays();
            	session.persist(vacation); 
            } else _vacation.update(vacation);
            
            transaction.commit();
            return vacation;           
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("VacationDao save: " + e.getMessage());
            return null;          
        }finally {
			 session.close();
		}
    }
    
    public void delete(Vacation vacation) {
    	Session session = null;
		Transaction transaction = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(vacation);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }           
            LOG.error("VacationDao delete: " + e.getMessage());
        }finally {
        	session.close();
		}
    }
    
    public void deleteAll() {
    	Session session = null;
		Transaction transaction = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createMutationQuery("DELETE FROM Vacation").executeUpdate(); 
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }           
            LOG.error("VacationDao deleteAll: " + e.getMessage());
        }finally {
        	session.close();
		}
    }
    
    public List<Vacation> findAll() {
    	List<Vacation> list = null;
    	Session session = null;
    	try {   	
    		session = HibernateUtil.getSessionFactory().openSession();
    		list = session.createQuery("SELECT vacation FROM Vacation AS vacation", Vacation.class).getResultList(); 
    	}catch (Exception e) {	 
    		 LOG.error("VacationDao findAll: " + e.getMessage());
        }finally {
        	session.close();
		}
    	return list;      
    }
    
    public Vacation findById(Long id){
    	Vacation vacation = null;  
    	Session session = null;
    	try  {
    		session = HibernateUtil.getSessionFactory().openSession();
    		vacation = session.get(Vacation.class, id);
        } catch (Exception e) {
        	LOG.error("VacationDao findById: " + e.getMessage());
        }finally {
        	session.close();
		}	
    	return vacation;
    }
    
    public List<Vacation> findByPerson(Person person) {
    	List<Vacation> list = null;
    	Session session = null;
    	try {  
    		session = HibernateUtil.getSessionFactory().openSession();
    		String hql = "SELECT vacation FROM Vacation AS vacation WHERE vacation.person= :person";
    		Query<Vacation> query = session.createQuery(hql, Vacation.class);
    		query.setParameter("person", person);   		
    		list = query.getResultList();

    	}catch (Exception e) {	 
    		 LOG.error("VacationDao findByPerson: " + e.getMessage());
        }finally {
        	session.close();
		}
    	return list;      
    }
    
    public List<Vacation> findByPerson(String id) {
    	List<Vacation> list = null;   
    	Session session = null;
    	try {  	
    		session = HibernateUtil.getSessionFactory().openSession();
    		list = session.createQuery("SELECT vacation FROM Vacation AS vacation WHERE vacation.person.id = :id", Vacation.class)
    				.setParameter("id", id)
    				.getResultList(); 
    	}catch (Exception e) {	 
    		 LOG.error("VacationDao findByPerson: " + e.getMessage());
        }finally {
        	session.close();
		}
    	return list;      
    }
}
