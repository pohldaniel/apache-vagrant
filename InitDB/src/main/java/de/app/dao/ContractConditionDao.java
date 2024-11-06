package de.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.app.entities.ContractCondition;
import de.app.entities.Person;
import de.app.utils.HibernateUtilMySql;
import de.app.utils.IHibernateUtil;

public class ContractConditionDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(ContractConditionDao.class);
	private static ContractConditionDao instance;
	private static IHibernateUtil HibernateUtil = HibernateUtilMySql.getInstance();

	private ContractConditionDao() {}
	 
	public static ContractConditionDao getInstance() {
	    if(ContractConditionDao.instance == null) {
	    	ContractConditionDao.instance = new ContractConditionDao();
	    }	   
	    return ContractConditionDao.instance;
	}
	
	public static ContractConditionDao getInstance(IHibernateUtil hibernateUtil) {
		ContractConditionDao.HibernateUtil = hibernateUtil;
	    return getInstance();
	}
	
	public void setDefaultConnection(IHibernateUtil hibernateUtil) {
		HibernateUtil = hibernateUtil;
	}
	
	public ContractCondition save(ContractCondition contractCondition) {
		Session session = null;
		Transaction transaction = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            ContractCondition _contractCondition = contractCondition.getId() == null ? null : session.find(ContractCondition.class, contractCondition.getId());
            if(_contractCondition == null) session.persist(contractCondition); else _contractCondition.update(contractCondition);            
            transaction.commit();
            return contractCondition;           
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("ContractConditionDao save: " + e.getMessage());
            return null;          
        }finally {
			 session.close();
		}
    }
    
    public void delete(ContractCondition contractCondition) {
    	Session session = null;
		Transaction transaction = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(contractCondition);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }           
            LOG.error("ContractConditionDao delete: " + e.getMessage());
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
            session.createMutationQuery("DELETE FROM ContractCondition").executeUpdate(); 
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }           
            LOG.error("ContractConditionDao deleteAll: " + e.getMessage());
        }finally {
        	session.close();
		}
    }
    
    public List<ContractCondition> findAll() {
    	List<ContractCondition> list = null;
    	Session session = null;
    	try {   	
    		session = HibernateUtil.getSessionFactory().openSession();
    		list = session.createQuery("SELECT contractCondition FROM ContractCondition AS contractCondition", ContractCondition.class).getResultList(); 
    	}catch (Exception e) {	 
    		 LOG.error("ContractConditionDao findAll: " + e.getMessage());
        }finally {
        	session.close();
		}
    	return list;      
    }
    
    public ContractCondition findById(Long id){
    	ContractCondition contractCondition = null;  
    	Session session = null;
    	try  {
    		session = HibernateUtil.getSessionFactory().openSession();
    		contractCondition = session.get(ContractCondition.class, id);
        } catch (Exception e) {
        	LOG.error("ContractConditionDao findById: " + e.getMessage());
        }finally {
        	session.close();
		}	
    	return contractCondition;
    }
    
    public List<ContractCondition> findByPerson(Person person) {
    	List<ContractCondition> list = null;
    	Session session = null;
    	try {  
    		session = HibernateUtil.getSessionFactory().openSession();
    		String hql = "SELECT contractCondition FROM ContractCondition AS contractCondition WHERE contractCondition.person= :person";
    		Query<ContractCondition> query = session.createQuery(hql, ContractCondition.class);
    		query.setParameter("person", person);   		
    		list = query.getResultList();

    	}catch (Exception e) {	 
    		 LOG.error("ContractConditionDao findByPerson: " + e.getMessage());
        }finally {
        	session.close();
		}
    	return list;      
    }
    
    public List<ContractCondition> findByPerson(String id) {
    	List<ContractCondition> list = null;   
    	Session session = null;
    	try {  	
    		session = HibernateUtil.getSessionFactory().openSession();
    		String hql = "SELECT contractCondition FROM ContractCondition AS contractCondition WHERE contractCondition.person.id = :id";
    		list = session.createQuery(hql, ContractCondition.class)
    				.setParameter("id", id)
    				.getResultList(); 
    	}catch (Exception e) {	 
    		 LOG.error("ContractConditionDao findByPerson: " + e.getMessage());
        }finally {
        	session.close();
		}
    	return list;      
    }
    
    public ContractCondition getCurrentContractCondition(String id) {
    	ContractCondition result = null;   
    	Session session = null;
    	try {  	
    		
    		String hql =  "SELECT contractCondition FROM ContractCondition AS contractCondition "
    		            + "WHERE contractCondition.person.id = :id "
    		            + "AND contractCondition.current = TRUE";
    		           

    		session = HibernateUtil.getSessionFactory().openSession();
    		Query<ContractCondition> query = session.createQuery(hql, ContractCondition.class);
    		query.setParameter("id", id);
    		query.setFirstResult(0);
    		query.setMaxResults(1); 
    		result = query.uniqueResult();
    		
    	}catch (Exception e) {	 
    		 LOG.error("ContractConditionDao getCurrentContractCondition: " + e.getMessage());
        }finally {
        	session.close();
		}  	
    	return result;
    }
    
}
