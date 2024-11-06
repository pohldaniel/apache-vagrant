package de.app;

import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

import de.app.utils.HibernateUtilMySql;
import de.app.services.TimesheetService;
import de.app.database.InitDatabase;
import de.app.utils.HibernateConfig;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@PropertySource({"classpath:application.properties"})
public class Main extends SpringBootServletInitializer{

	@Autowired
	TimesheetService timesheetService;
	
	public static void main(String[] args) { 
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Berlin"));
		Locale.setDefault(Locale.GERMANY);
        SpringApplication.run(Main.class, args);    	
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(new Class[] { Main.class});
    }
    
    @PostConstruct
	public void init() throws Exception { 
    	HibernateUtilMySql.getInstance().createSessionFactory("jdbc:mysql://localhost:3306/timeradb?useSSL=false&allowPublicKeyRetrieval=true", "dbmanager", "salt");
    	//HibernateUtilMySql.getInstance().createSessionFactory("jdbc:mysql://localhost:3306/timeradb_gen?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
    	HibernateConfig.setConnectionMySql();
    	
    	InitDatabase.getInstance().setTimesheetService(timesheetService);		
		InitDatabase.getInstance().emptyRepositories();
		InitDatabase.getInstance().fillRepositories();
		System.exit(0);
    }
}
