package de.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.app.entities.Person;
import de.app.entities.Project;
import de.app.utils.HibernateUtilMySql;
import de.app.utils.IHibernateUtil;

@SuppressWarnings("unused")
public class ProjectDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProjectDao.class);
	private static ProjectDao instance;
	private static IHibernateUtil HibernateUtil = HibernateUtilMySql.getInstance();

	private ProjectDao() {}
	 
	public static ProjectDao getInstance() {
	    if(ProjectDao.instance == null) {
	    	ProjectDao.instance = new ProjectDao();
	    }	   
	    return ProjectDao.instance;
	}
	
	public static ProjectDao getInstance(IHibernateUtil hibernateUtil) {
		ProjectDao.HibernateUtil = hibernateUtil;
	    return getInstance();
	}
	
	public void setDefaultConnection(IHibernateUtil hibernateUtil) {
		HibernateUtil = hibernateUtil;
	}
	
	public Project save(Project project) {
		Session session = null;
		Transaction transaction = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            Project _project = project.getId() == null ? null : session.find(Project.class, project.getId());
            if(_project == null) session.persist(project); else _project.update(project);            
            transaction.commit();
            return project;           
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("ProjectDao save: " + e.getMessage());
            return null;          
        }finally {
			 session.close();
		}
    }
    
    public void delete(Project project) {
    	Session session = null;
		Transaction transaction = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            /*NativeQuery<Object> nativeQuery = session.createNativeQuery("DELETE FROM PROJECT_PERSON WHERE PROJECT_ID = '" + project.getId() + "'", Object.class);
    		nativeQuery.executeUpdate();*/
    		
            session.remove(project);
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }           
            LOG.error("ProjectDao delete: " + e.getMessage());
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
            session.createMutationQuery("DELETE FROM Project").executeUpdate(); 
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }           
            LOG.error("ProjectDao deleteAll: " + e.getMessage());
        }finally {
        	session.close();
		}
    }
    
    public List<Project> findAll() {
    	List<Project> list = null;
    	Session session = null;
    	try {   	
    		session = HibernateUtil.getSessionFactory().openSession();
    		list = session.createQuery("SELECT project FROM Project AS project", Project.class).getResultList(); 
    	}catch (Exception e) {	 
    		 LOG.error("ProjectDao findAll: " + e.getMessage());
        }finally {
        	session.close();
		}
    	return list;      
    }
    
    public Project findById(Long id){
    	Project project = null;  
    	Session session = null;
    	try  {
    		session = HibernateUtil.getSessionFactory().openSession();
    		project = session.get(Project.class, id);
        } catch (Exception e) {
        	LOG.error("ProjectDao findById: " + e.getMessage());
        }finally {
        	session.close();
		}	
    	return project;
    }
    
    public List<Project> findByPerson(Person person) {
    	List<Project> list = null;
    	Session session = null;
    	try {  
    		session = HibernateUtil.getSessionFactory().openSession();
    		String hql = "SELECT project FROM Project AS project INNER JOIN project.persons AS person WHERE person = :person ORDER BY project.id DESC";
    		Query<Project> query = session.createQuery(hql, Project.class);
    		query.setParameter("person", person);   		
    		list = query.getResultList();

    	}catch (Exception e) {	 
    		 LOG.error("ProjectDao findByPerson: " + e.getMessage());
        }finally {
        	session.close();
		}
    	return list;      
    }
    
    public List<Project> findByPerson(String id) {
    	List<Project> list = null;   
    	Session session = null;
    	try {  	
    		session = HibernateUtil.getSessionFactory().openSession();
    		list = session.createQuery("SELECT project FROM Project AS project INNER JOIN project.persons AS person WHERE person.id = :id", Project.class)
    				.setParameter("id", id)
    				.getResultList(); 
    	}catch (Exception e) {	 
    		 LOG.error("ProjectDao findByPerson: " + e.getMessage());
        }finally {
        	session.close();
		}
    	return list;      
    }
}
