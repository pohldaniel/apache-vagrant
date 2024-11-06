package de.app.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.app.entities.Person;
import de.app.entities.Project;
import de.app.entities.Timesheet;
import de.app.utils.HibernateUtilMySql;
import de.app.utils.IHibernateUtil;

public class TimesheetDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(TimesheetDao.class);
	private static TimesheetDao instance;
	private static IHibernateUtil HibernateUtil = HibernateUtilMySql.getInstance();

	private TimesheetDao() {}
	
	public static TimesheetDao getInstance() {
	    if(TimesheetDao.instance == null) {
	    	TimesheetDao.instance = new TimesheetDao();
	    }	   
	    return TimesheetDao.instance;
	}
	
	public static TimesheetDao getInstance(IHibernateUtil hibernateUtil) {	    
	    TimesheetDao.HibernateUtil = hibernateUtil;
	    return getInstance();
	}
	
	public void setDefaultConnection(IHibernateUtil hibernateUtil) {
		HibernateUtil = hibernateUtil;
	}
	
	public Timesheet save(Timesheet timesheet) {
		Session session = null;
		Transaction transaction = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            Project project = timesheet.getProject() == null ? null : session.find(Project.class, timesheet.getProject().getId());
            timesheet.setProject(project);
                      
            Timesheet _timesheet = timesheet.getId() == null ? null : session.find(Timesheet.class, timesheet.getId());
            if(_timesheet == null) {
            	timesheet.updateDifference();
            	session.persist(timesheet); 
            }else 
            	_timesheet.update(timesheet);
            
            transaction.commit();
            return timesheet;           
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("TimesheetDao save: " + e.getMessage());
            return null;          
        } finally {
			 session.close();
		}
    }
    
	public void delete(Timesheet timesheet) {
    	Session session = null;
		Transaction transaction = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(timesheet);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }           
            LOG.error("TimesheetDao delete: " + e.getMessage());
        } finally {
        	session.close();
		}
    }
	
    public void delete(Long id) {
    	delete(findById(id));
    }
    
    public void deleteAll() {
    	Session session = null;
		Transaction transaction = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createMutationQuery("DELETE FROM Timesheet").executeUpdate(); 
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }           
            LOG.error("TimesheetDao deleteAll: " + e.getMessage());
        } finally {
        	session.close();
		}
    }
    
    public List<Timesheet> findAll() {
    	List<Timesheet> list = null;
    	Session session = null;
    	try {   	
    		session = HibernateUtil.getSessionFactory().openSession();
    		list = session.createQuery("SELECT timesheet FROM Timesheet AS timesheet", Timesheet.class).getResultList(); 
    	}catch (Exception e) {	 
    		 LOG.error("TimesheetDao findAll: " + e.getMessage());
        } finally {
        	session.close();
		}
    	return list;      
    }
    
    public Timesheet findById(Long id){
    	Timesheet timesheet = null;  
    	Session session = null;
    	try  {
    		session = HibernateUtil.getSessionFactory().openSession();
    		timesheet = session.get(Timesheet.class, id);
        } catch (Exception e) {
        	LOG.error("TimesheetDao findById: " + e.getMessage());
        } finally {
        	session.close();
		}	
    	return timesheet;
    }
    
    public List<Timesheet> findByPerson(Person person) {
    	List<Timesheet> list = null;
    	Session session = null;
    	try {  
    		session = HibernateUtil.getSessionFactory().openSession();
    		String hql = "SELECT timesheet FROM Timesheet AS timesheet WHERE timesheet.person= :person";
    		Query<Timesheet> query = session.createQuery(hql, Timesheet.class);
    		query.setParameter("person", person);   		
    		list = query.getResultList();

    	}catch (Exception e) {	 
    		 LOG.error("TimesheetDao findByPerson: " + e.getMessage());
        }finally {
        	session.close();
		}
    	return list;      
    }
    
    public List<Timesheet> findByPerson(String id) {
    	List<Timesheet> list = null;   
    	Session session = null;
    	try {  	
    		session = HibernateUtil.getSessionFactory().openSession();
    		list = session.createQuery("SELECT timesheet FROM Timesheet AS timesheet WHERE timesheet.person.id = :id", Timesheet.class)
    				.setParameter("id", id)
    				.getResultList(); 
    	}catch (Exception e) {	 
    		 LOG.error("TimesheetDao findByPerson: " + e.getMessage());
        }finally {
        	session.close();
		}
    	return list;      
    }
    
    public List<Timesheet> findByPersonInRange(String id, Calendar from, Calendar until) {
    	List<Timesheet> list = null;   
    	Session session = null;
    	try {  	
    		session = HibernateUtil.getSessionFactory().openSession();
    		list = session.createQuery("SELECT timesheet FROM Timesheet AS timesheet WHERE timesheet.person.id = :id AND CAST(timesheet.startTime AS DATE) BETWEEN :from AND :until", Timesheet.class)
    				.setParameter("id", id)
    				.setParameter("from", from)
    				.setParameter("until", until)
    				.getResultList(); 
    	}catch (Exception e) {	 
    		 LOG.error("TimesheetDao findByPersonInRange: " + e.getMessage());
        }finally {
        	session.close();
		}
    	return list;      
    }
    
    public List<Timesheet> findByPersonAndDate(String id, Calendar date) {
    	List<Timesheet> list = null;   
    	Session session = null;
    	try {  	
    		
    		String hql =  "SELECT "
    		            + "timesheet FROM Timesheet AS timesheet "
    		            + "WHERE timesheet.person.id = :id "
    		            + "AND YEAR(timesheet.startTime) = YEAR(:date) "
    		            + "AND MONTH(timesheet.startTime) = MONTH(:date) "
    		            + "AND DAY(timesheet.startTime) = DAY(:date) "
    		            + "ORDER BY startTime DESC";
    		
    		session = HibernateUtil.getSessionFactory().openSession();
    		list = session.createQuery(hql, Timesheet.class)
    				.setParameter("id", id)
    				.setParameter("date", date)
    				.getResultList(); 
    	}catch (Exception e) {	 
    		 LOG.error("TimesheetDao findByPersonAndDate: " + e.getMessage());
        }finally {
        	session.close();
		}
    	return list;      
    }
    
    public int getSummary(Timesheet timesheet) {
    	int result = 0;   
    	Session session = null;
    	try {  	
    		
    		String hql =  "SELECT sum(timesheet.difference) AS summary "
    		            + "FROM Timesheet AS timesheet "
    		            + "WHERE timesheet.person.id = :id "
    		            + "AND timesheet.startTime <= :startTime "
    		            + "AND YEAR(timesheet.startTime) = YEAR(:startTime) "
    		            + "AND MONTH(timesheet.startTime) = MONTH(:startTime) "
    		            + "AND DAY(timesheet.startTime) = DAY(:startTime) "
    		            + "GROUP BY CAST(startTime AS DATE)";

    		session = HibernateUtil.getSessionFactory().openSession();
    		Query<Long> query = session.createQuery(hql, Long.class);
    		query.setParameter("id", timesheet.getPerson().getId());
    		query.setParameter("startTime", timesheet.getStartTime());
    		query.setFirstResult(0);
    		query.setMaxResults(1); 
    		result = query.uniqueResult().intValue();
    		
    	}catch (Exception e) {	 
    		 LOG.error("TimesheetDao getSummary: " + e.getMessage());
        }finally {
        	session.close();
		}  	
    	return result;
    }
    
    public int getWorktimeOfMonth(String id, Calendar month) {
    	
    	int result = 0;   
    	Session session = null;
    	try {  	
    		
    		String hql =  "SELECT sum(timesheet.difference) AS worktime "
		            + "FROM Timesheet AS timesheet "
		            + "WHERE timesheet.person.id = :id "
		            + "AND YEAR(timesheet.startTime) = YEAR(:date) "
		            + "AND MONTH(timesheet.startTime) = MONTH(:date) "
		            + "GROUP BY YEAR(startTime), MONTH(startTime)";
    		

    		session = HibernateUtil.getSessionFactory().openSession();
    		Query<Long> query = session.createQuery(hql, Long.class);
    		query.setParameter("id", id);
    		query.setParameter("date", month);
    		query.setFirstResult(0);
    		query.setMaxResults(1); 
    		result = query.uniqueResult().intValue();
    		
    	}catch (Exception e) {	 
    		 LOG.error("TimesheetDao getWorktimeOfMonth: " + e.getMessage());
        }finally {
        	session.close();
		}  	    	
    	return result;
    }
}
