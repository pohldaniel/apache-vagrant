package de.app.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.app.entities.TechnicalKey;
import de.app.utils.HibernateUtilMySql;
import de.app.utils.IHibernateUtil;

public class TechnicalKeyDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(TechnicalKeyDao.class);
	private static TechnicalKeyDao instance;
	private static IHibernateUtil HibernateUtil = HibernateUtilMySql.getInstance();

	private TechnicalKeyDao() {}
	
	public static TechnicalKeyDao getInstance() {
	    if(TechnicalKeyDao.instance == null) {
	    	TechnicalKeyDao.instance = new TechnicalKeyDao();
	    }	   
	    return TechnicalKeyDao.instance;
	}
	
	public static TechnicalKeyDao getInstance (IHibernateUtil hibernateUtil) {	    
	    TechnicalKeyDao.HibernateUtil = hibernateUtil;
	    return getInstance();
	}
	
	public void setDefaultConnection(IHibernateUtil hibernateUtil) {
		HibernateUtil = hibernateUtil;
	}
	
	public TechnicalKey save(TechnicalKey technicalKey) {
		Session session = null;
		Transaction transaction = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            TechnicalKey _technicalKey = technicalKey.getPrimaryKeyName() == null ? null : session.find(TechnicalKey.class, technicalKey.getPrimaryKeyName());
            if(_technicalKey == null) session.persist(technicalKey); else _technicalKey.update(technicalKey);
            
            transaction.commit();
            return technicalKey;           
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("TechnicalKeyDao save: " + e.getMessage());
            return null;          
        } finally {
			 session.close();
		}
    }
    
    public void delete(TechnicalKey technicalKey) {
    	Session session = null;
		Transaction transaction = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(technicalKey);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }           
            LOG.error("TechnicalKeyDao delete: " + e.getMessage());
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
            session.createMutationQuery("DELETE FROM TechnicalKey").executeUpdate(); 
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }           
            LOG.error("TechnicalKeyDao deleteAll: " + e.getMessage());
        } finally {
        	session.close();
		}
    }
}
