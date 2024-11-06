package de.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.app.entities.Profile;
import de.app.utils.HibernateUtilMySql;
import de.app.utils.IHibernateUtil;

public class ProfileDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProfileDao.class);
	private static ProfileDao instance;
	private static IHibernateUtil HibernateUtil = HibernateUtilMySql.getInstance();

	private ProfileDao() {}
	 
	public static ProfileDao getInstance() {
	    if(ProfileDao.instance == null) {
	    	ProfileDao.instance = new ProfileDao();
	    }	   
	    return ProfileDao.instance;
	}
	
	public static ProfileDao getInstance(IHibernateUtil hibernateUtil) {
		ProfileDao.HibernateUtil = hibernateUtil;
	    return getInstance();
	}
	
	public void setDefaultConnection(IHibernateUtil hibernateUtil) {
		HibernateUtil = hibernateUtil;
	}
	
	public Profile save(Profile profile) {
		Session session = null;
		Transaction transaction = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            Profile _profile = profile.getId() == null ? null : session.find(Profile.class, profile.getId());
            if(_profile == null) session.persist(profile); else _profile.update(profile);            
            transaction.commit();
            return profile;           
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("ProfileDao save: " + e.getMessage());
            return null;          
        }finally {
			 session.close();
		}
    }
    
    public void delete(Profile profile) {
    	Session session = null;
		Transaction transaction = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(profile);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }           
            LOG.error("ProfileDao delete: " + e.getMessage());
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
            session.createMutationQuery("DELETE FROM Profile").executeUpdate(); 
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }           
            LOG.error("ProfileDao deleteAll: " + e.getMessage());
        }finally {
        	session.close();
		}
    }
    
    public List<Profile> findAll() {
    	List<Profile> list = null;
    	Session session = null;
    	try {   	
    		session = HibernateUtil.getSessionFactory().openSession();
    		list = session.createQuery("SELECT profile FROM Profile AS profile", Profile.class).getResultList(); 
    	}catch (Exception e) {	 
    		 LOG.error("ProfileDao findAll: " + e.getMessage());
        }finally {
        	session.close();
		}
    	return list;      
    }
    
    public Profile findById(String id){
    	Profile profile = null;  
    	Session session = null;
    	try  {
    		session = HibernateUtil.getSessionFactory().openSession();
    		profile = session.get(Profile.class, id);
        } catch (Exception e) {
        	LOG.error("ProfileDao findById: " + e.getMessage());
        }finally {
        	session.close();
		}	
    	return profile;
    }
}
