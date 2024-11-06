package de.app.utils;

import de.app.dao.ContractConditionDao;
import de.app.dao.PersonDao;
import de.app.dao.ProfileDao;
import de.app.dao.ProjectDao;
import de.app.dao.TechnicalKeyDao;
import de.app.dao.TimesheetDao;
import de.app.dao.VacationDao;

public class HibernateConfig {
	public static void setConnectionMySql() {
		PersonDao.getInstance().setDefaultConnection(HibernateUtilMySql.getInstance());	
		TechnicalKeyDao.getInstance().setDefaultConnection(HibernateUtilMySql.getInstance());		
		ProfileDao.getInstance().setDefaultConnection(HibernateUtilMySql.getInstance());
		TimesheetDao.getInstance().setDefaultConnection(HibernateUtilMySql.getInstance());
		ContractConditionDao.getInstance().setDefaultConnection(HibernateUtilMySql.getInstance());		
		VacationDao.getInstance().setDefaultConnection(HibernateUtilMySql.getInstance());
		ProjectDao.getInstance().setDefaultConnection(HibernateUtilMySql.getInstance());
	}
}
