package de.app.database;

import de.app.dao.ContractConditionDao;
import de.app.dao.PersonDao;
import de.app.dao.ProjectDao;
import de.app.dao.TechnicalKeyDao;
import de.app.dao.TimesheetDao;
import de.app.dao.VacationDao;
import de.app.entities.ContractCondition;
import de.app.entities.Person;
import de.app.entities.Project;
import de.app.entities.TechnicalKey;
import de.app.entities.Timesheet;
import de.app.entities.Vacation;
import de.app.entities.builder.AddressBuilder;
import de.app.entities.builder.CommunicationBuilder;
import de.app.entities.builder.ContractConditionBuilder;
import de.app.entities.builder.PersonBuilder;
import de.app.entities.builder.ProfileBuilder;
import de.app.entities.builder.ProjectBuilder;
import de.app.entities.builder.TechnicalKeyBuilder;
import de.app.entities.builder.TimesheetBuilder;
import de.app.entities.builder.VacationBuilder;
import de.app.entities.enums.Category;
import de.app.entities.enums.Role;
import de.app.entities.enums.Status;
import de.app.entities.enums.VacationStatus;
import de.app.services.TimesheetService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitDatabase {
	
	private static final Logger LOG = LoggerFactory.getLogger(InitDatabase.class);
	
	private PersonDao personDao = PersonDao.getInstance();
	private TimesheetDao timesheetDao = TimesheetDao.getInstance();
	private TechnicalKeyDao technicalKeyDao = TechnicalKeyDao.getInstance();
	private VacationDao vacationDao = VacationDao.getInstance();
	private ContractConditionDao contractConditionDao = ContractConditionDao.getInstance();
	private ProjectDao projectDao = ProjectDao.getInstance();
	
	public static final ArrayList<Person> personList = new ArrayList<Person>();
	

	protected TimesheetService timesheetService;
	
	private Calendar calendar8Am = getTimeStamp(Calendar.getInstance());
	//private Calendar calendar8Am = getTimeStamp("26-09-2024 08:00:00 000", "dd'-'MM'-'yyyy' 'HH':'mm':'ss' 'SSS'ms'");
	
	private static InitDatabase instance;
	
	private InitDatabase() {}
	 
	public static InitDatabase getInstance () {
	    if (InitDatabase.instance == null) {
	    	InitDatabase.instance = new InitDatabase();
	    }
	    return InitDatabase.instance;
	}
	
	public void setTimesheetService(TimesheetService timesheetService) {
		this.timesheetService = timesheetService;
	}

    public void emptyRepositories() {      
    	personDao.deleteAll();    
    	technicalKeyDao.deleteAll();
    	timesheetDao.deleteAll();
    	vacationDao.deleteAll();
    	contractConditionDao.deleteAll();
    	projectDao.deleteAll();    
    	personList.clear();
    }

    public void fillRepositories() {
    	technicalKeyDao.save(tk1);
        technicalKeyDao.save(tk2);
        technicalKeyDao.save(tk3);
        technicalKeyDao.save(tk4);
        
        personList.add(p6);
        personList.add(p7);
        personList.add(p8);
        personList.add(p9);
        personList.add(p10);
        personList.add(p11);
        
        
        p6.setProfile(new ProfileBuilder()
        		.id(p6)
        		.birthday(getTimeStamp("1965-10-08", "yyyy'-'MM'-'dd"))
        		.memberSince(getTimeStamp("1996-12-13 10:00:00", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
        		.locale(Locale.GERMANY)
        		.build());
        
        p6.setAddress(new AddressBuilder()
        		.id(p6)
        		.zipCode("71560")
        		.city("Sulzbach")
        		.street("Ludwigstr.")
        		.houseNumber("20")
        		.build());
        
        p6.setCommunication(new CommunicationBuilder()
        		.id(p6)
        		.email("j.nicolai@main-gruppe.de")
        		.phonePrivate("0171/4230731")
        		.build());
        
        p7.setProfile(new ProfileBuilder()
        		.id(p7)
        		.birthday(getTimeStamp("1989-05-08", "yyyy'-'MM'-'dd"))
        		.memberSince(getTimeStamp("2019-05-02 07:40:29", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
        		.locale(Locale.GERMANY)
        		.build());
        
        p7.setAddress(new AddressBuilder()
        		.id(p7)
        		.zipCode("04157")
        		.city("Leipzig")
        		.street("Virchowstr.")
        		.houseNumber("23")
        		.build());
        p7.setCommunication(new CommunicationBuilder()
        		.id(p7)
        		.email("d.pohl@main-gruppe.de")
        		.phonePrivate("0172/3750854")
        		.build());
        
        p8.setProfile(new ProfileBuilder()
        		.id(p8)
        		.birthday(getTimeStamp("1989-05-08", "yyyy'-'MM'-'dd"))
        		.memberSince(getTimeStamp("2019-05-02 07:40:29", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
        		.locale(Locale.GERMANY)
        		.build());
        
        p8.setAddress(new AddressBuilder()
        		.id(p8)
        		.zipCode("04157")
        		.city("Leipzig")
        		.street("Virchowstr.")
        		.houseNumber("23")
        		.build());
        p8.setCommunication(new CommunicationBuilder()
        		.id(p8)
        		.email("d.pohl@main-gruppe.de")
        		.phonePrivate("0172/3750854")
        		.build());
        
        p9.setProfile(new ProfileBuilder()
        		.id(p9)
        		.birthday(getTimeStamp("1994-08-15", "yyyy'-'MM'-'dd"))
        		.memberSince(getTimeStamp("2024-06-01 08:00:00", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
        		.locale(Locale.GERMANY)
        		.build());
        
        p9.setAddress(new AddressBuilder()
        		.id(p9)
        		.zipCode("04109")
        		.city("Leipzig")
        		.street("Zentralstr.")
        		.houseNumber("4")
        		.build());
        
        p9.setCommunication(new CommunicationBuilder()
        		.id(p9)
        		.email("d.beresowsky@main-gruppe.de")
        		.phonePrivate("0176/85991519")
        		.build());
     
        p10.setProfile(new ProfileBuilder()
        		.id(p10)
        		.birthday(getTimeStamp("1962-04-14", "yyyy'-'MM'-'dd"))
        		.memberSince(getTimeStamp("2001-01-01 10:00:00", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
        		.locale(Locale.GERMANY)
        		.build());
        
        p10.setAddress(new AddressBuilder()
        		.id(p10)
        		.zipCode("04129")
        		.city("Leipzig")
        		.street("Thaerstr.")
        		.houseNumber("38")
        		.build());
        
        p10.setCommunication(new CommunicationBuilder()
        		.id(p10)
        		.email("r.baufeld@main-gruppe.de")
        		.phonePrivate("015172207149")
        		.phoneWork("0341/9982006")
        		.build());
               
        p11.setProfile(new ProfileBuilder()
        		.id(p11)
        		.birthday(getTimeStamp("1960-03-12", "yyyy'-'MM'-'dd"))
        		.memberSince(getTimeStamp("1996-12-13 10:00:00", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
        		.locale(Locale.GERMANY)
        		.build());
        
        p11.setAddress(new AddressBuilder()
        		.id(p11)
        		.zipCode("04289")
        		.city("Leipzig")
        		.street("Preußenstr.")
        		.houseNumber("17")
        		.build());
        
        p11.setCommunication(new CommunicationBuilder()
        		.id(p11)
        		.email("h.thom@main-gruppe.de")
        		.phonePrivate("0171/6913119")
        		.phoneWork("0341/9982017")
        		.build());
        
        personDao.save(p1); 
        personDao.save(p2);
        personDao.save(p3);
        personDao.save(p4);

        personDao.save(p5);
        personDao.save(p6);
        personDao.save(p7);
        personDao.save(p8);
        personDao.save(p9);
        personDao.save(p10);
        personDao.save(p11);
          
        projectDao.save(pr1);
        projectDao.save(pr2);
        projectDao.save(pr3);
        
        Timesheet ts;
        for(Person person : personList) {
        	ts = new TimesheetBuilder()
            		.person(person)
                    .startTime(addDays(calendar8Am, -1))
                    .endTime(addHours(addDays(calendar8Am, -1), 4))
                    .category(Category.WORKTIME)
                    .task("Timera")
                    .project(pr1)
                    .build();
        	
        	if(ts.getStartTime().get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && ts.getStartTime().get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
        		ts = timesheetDao.save(ts);
        	
        	ts = new TimesheetBuilder()
            		.person(person)
                    .startTime(addMinutes(ts.getEndTime(), 30))
                    .endTime(addMinutes(ts.getEndTime(), 270))
                    .category(Category.WORKTIME)
                    .task("Timera")
                    .project(pr1)
                    .build();
        	
        	if(ts.getStartTime().get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && ts.getStartTime().get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
        		ts = timesheetDao.save(ts);
        	
        	timesheetService.updateSummary(ts);
        	for(int i = 0; i < 180; i++) {
	        	ts = new TimesheetBuilder()
	            		.person(person)
	            		.startTime(addMinutes(addDays(ts.getStartTime(), -1), -270))
	                    .endTime(addMinutes(addDays(ts.getEndTime(), -1), -270))
	                    .category(Category.WORKTIME)
	                    .task("Timera")
	                    .project(pr1)
	                    .build();
	        	
	        	if(ts.getStartTime().get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && ts.getStartTime().get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
	        		ts = timesheetDao.save(ts);
	        	
	        	ts = new TimesheetBuilder()
	            		.person(person)
	                    .startTime(addMinutes(ts.getEndTime(), 30))
	                    .endTime(addMinutes(ts.getEndTime(), 270))
	                    .category(Category.WORKTIME)
	                    .task("Timera")
	                    .project(pr1)
	                    .build();
	        	
	        	if(ts.getStartTime().get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && ts.getStartTime().get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
	        		ts = timesheetDao.save(ts);
	        	
	        	timesheetService.updateSummary(ts);
        	}
        }

        vacationDao.save(v1);
        vacationDao.save(v2);
        vacationDao.save(v3);
        vacationDao.save(v4);
        vacationDao.save(v5);
        vacationDao.save(v6);
        vacationDao.save(v7);
        vacationDao.save(v8);
        vacationDao.save(v9);
        vacationDao.save(v10);
        
        contractConditionDao.save(c1);
        contractConditionDao.save(c2);
        contractConditionDao.save(c3);
        contractConditionDao.save(c4);
        contractConditionDao.save(c5);
        contractConditionDao.save(c6);
        contractConditionDao.save(c7);
        contractConditionDao.save(c8);
        contractConditionDao.save(c9);
    }

    private TechnicalKey tk1 = new TechnicalKeyBuilder()
    		.primaryKeyName("timesheet_id")
    		.currentValue(0l).build();
    
    private TechnicalKey tk2 = new TechnicalKeyBuilder()
    		.primaryKeyName("vacation_id")
    		.currentValue(0l).build();
    
    private TechnicalKey tk3 = new TechnicalKeyBuilder()
    		.primaryKeyName("contract_condition_id")
    		.currentValue(0l).build();
    
    private TechnicalKey tk4 = new TechnicalKeyBuilder()
    		.primaryKeyName("project_id")
    		.currentValue(0l).build();
    
    private Person p1 = new PersonBuilder()
            .id("admin")
            .prename("Anke")
            .surname("Admin")
            .mail("test@test.de")
            .externalCompany("N.N.")
            .passwordHash("73b3b928c7bf55e29d7d8e2840eadc")
            .roles(Role.ADMIN, Role.USER)
            .build();
    private Person p2 = new PersonBuilder()
            .id("actionmanager")
            .prename("Alex")
            .surname("Actionmanager")
            .mail("test@test.de")
            .externalCompany("N.N.")
            .passwordHash("73b3b928c7bf55e29d7d8e2840eadc")
            .roles(Role.ACTION_MANAGER)
            .build();
    private Person p3 = new PersonBuilder()
            .id("topicmanager")
            .prename("Rolf")
            .surname("Topicmanager")
            .mail("test@test.de")
            .externalCompany("N.N.")
            .passwordHash("73b3b928c7bf55e29d7d8e2840eadc")
            .roles(Role.TOPICAREA_MANAGER)
            .build();
    private Person p4 = new PersonBuilder()
            .id("user")
            .prename("Bibi")
            .surname("User")
            .mail("test@test.de")
            .externalCompany("N.N.")
            .passwordHash("73b3b928c7bf55e29d7d8e2840eadc")
            .roles(Role.USER)
            .build();   
    private Person p5 = new PersonBuilder()
            .id("personmanager")
            .prename("Frank")
            .surname("Schulze")
            .mail("test@test.de")
            .externalCompany("N.N.")
            .passwordHash("73b3b928c7bf55e29d7d8e2840eadc")
            .roles(Role.PERSON_MANAGER)
            .build();
    private Person p6 = new PersonBuilder()
            .id("jnicolai")
            .prename("Jürgen")
            .surname("Nicolai")
            .mail("j.nicolai@main-gruppe.de")
            .externalCompany("N.N.")
            .passwordHash("7f13cb535270f67e99dd42b168cdb9")
            .roles(Role.ADMIN)
            .build();
    private Person p7 = new PersonBuilder()
            .id("dpohl")
            .prename("Daniel")
            .surname("Pohl")
            .mail("d.pohl@main-gruppe.de")
            .externalCompany("N.N.")
            .passwordHash("73b3b928c7bf55e29d7d8e2840eadc")
            .roles(Role.EMPLOYEE, Role.ADMIN)
            .build();
    private Person p8 = new PersonBuilder()
            .id("asambale")
            .prename("Alex")
            .surname("Sambale")
            .mail("test@test.de")
            .externalCompany("N.N.")
            .passwordHash("61986954d5ddb07e3d44b1da1b8535c")
            .roles(Role.ADMIN)
            .build();
    private Person p9 = new PersonBuilder()
            .id("nberesowsky")
            .prename("Nodira")
            .surname("Beresowsky")
            .mail("d.beresowsky@main-gruppe.de")
            .externalCompany("N.N.")
            .passwordHash("058618089dac289b87fb65df12c3475")
            .roles(Role.EMPLOYEE)
            .build();
    private Person p10 = new PersonBuilder()
            .id("rbaufeld")
            .prename("Ramona")
            .surname("Baufeld")
            .mail("r.baufeld@main-gruppe.de")
            .externalCompany("N.N.")
            .passwordHash("a7866424d073e412673c92b6ec8af7")
            .roles(Role.ADMIN)
            .build();
    private Person p11 = new PersonBuilder()
            .id("h.thom")
            .prename("Holger")
            .surname("Thom")
            .mail("h.thom@main-gruppe.de")
            .externalCompany("N.N.")
            .passwordHash("179ced643a228460ef27b2c83541cf9b")
            .roles(Role.ADMIN)
            .build();
    
    private Project pr1 = new ProjectBuilder()    	
    		.persons(p6, p7, p8, p9, p10, p11)
    		.id("main intern")
    		.company("Main {GRUPPE}")
    		.location("Leipzig")    		
    		.status(Status.ENABLED)
    		.build();
    
    private Project pr2 = new ProjectBuilder()   
    		.persons(p6, p7, p8, p9, p10, p11)
    		.id("ABS Workflow")
    		.company("Allianz")
    		.location("Stuttgart")    		
    		.status(Status.ENABLED)
    		.build();
    
    private Project pr3 = new ProjectBuilder()    
    		.persons(p6, p7, p8, p9, p10, p11)
    		.id("FISP")
    		.company("Solcom")
    		.location("Frankfurt am Main")   		
    		.status(Status.ENABLED)
    		.build();
   
    private Vacation v1 = new VacationBuilder()
            .person(p7)
            .createdAt(getTimeStamp("2018-10-19 16:38:45", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .startDate(getTimeStamp("2018-11-05 00:00:00", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .endDate(getTimeStamp("2018-11-09 23:59:59", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .employeeComment("Urlaub - bereits von der Allianz genehmigt")
            .managerComment("Genehmigt")
            .status(VacationStatus.APPROVED)
            .build();
    
    private Vacation v2 = new VacationBuilder()
            .person(p7)
            .createdAt(getTimeStamp("2018-11-05 07:53:52", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .startDate(getTimeStamp("2019-01-02 00:00:00", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .endDate(getTimeStamp("2019-01-04 23:59:59", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .employeeComment("Weihnachten")
            .managerComment("Genehmigt")
            .status(VacationStatus.APPROVED)
            .build();
    
    private Vacation v3 = new VacationBuilder()
            .person(p7)
            .createdAt(getTimeStamp("2019-01-09 14:11:50", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .startDate(getTimeStamp("2019-06-07 00:00:00", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .endDate(getTimeStamp("2019-06-14 23:59:59", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .employeeComment("Jahresurlaub 1")
            .managerComment("Genehmigt")
            .status(VacationStatus.APPROVED)
            .build();
    
    private Vacation v4 = new VacationBuilder()
            .person(p7)
            .createdAt(getTimeStamp("2019-01-09 14:12:30", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .startDate(getTimeStamp("2019-08-19 00:00:00", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .endDate(getTimeStamp("2019-08-30 23:59:59", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .employeeComment("Jahresurlaub 2")
            .managerComment("Genehmigt")
            .status(VacationStatus.APPROVED)
            .build();
    
    private Vacation v5 = new VacationBuilder()
            .person(p7)
            .createdAt(getTimeStamp("2019-01-10 09:51:36", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .startDate(getTimeStamp("2019-05-31 00:00:00", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .endDate(getTimeStamp("2019-05-31 23:59:59", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .employeeComment("Brückentag bei Christi Himmelfahrt")
            .managerComment("Genehmigt")
            .status(VacationStatus.APPROVED)
            .build();
    
    private Vacation v6 = new VacationBuilder()
            .person(p6)
            .createdAt(getTimeStamp("2019-02-18 17:28:41", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .startDate(getTimeStamp("2019-03-15 00:00:00", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .endDate(getTimeStamp("2019-03-20 23:59:59", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .employeeComment("Flugreise Türkei")
            .managerComment("Genehmigt")
            .status(VacationStatus.APPROVED)
            .build();
    
    private Vacation v7 = new VacationBuilder()
            .person(p6)
            .createdAt(getTimeStamp("2019-04-01 17:45:16", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .startDate(getTimeStamp("2019-04-15 00:00:00", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .endDate(getTimeStamp("2019-04-26 23:59:59", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .employeeComment("Osterurlaub")
            .managerComment("Genehmigt")
            .status(VacationStatus.APPROVED)
            .build();
    
    private Vacation v8 = new VacationBuilder()
            .person(p6)
            .createdAt(getTimeStamp("2019-04-24 06:01:27", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .startDate(getTimeStamp("2019-09-09 00:00:00", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .endDate(getTimeStamp("2019-09-11 23:59:59", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .employeeComment("Zur Einschulung meines Neffen in München")
            .managerComment("Genehmigt")
            .status(VacationStatus.APPROVED)
            .build();
    
    private Vacation v9 = new VacationBuilder()
            .person(p6)
            .createdAt(getTimeStamp("2019-04-24 10:25:34", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .startDate(getTimeStamp("2019-10-07 00:00:00", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .endDate(getTimeStamp("2019-10-11 23:59:59", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .employeeComment("Japanurlaub :D")
            .managerComment("Genehmigt")
            .status(VacationStatus.APPROVED)
            .build();
    
    private Vacation v10 = new VacationBuilder()
            .person(p6)
            .createdAt(getTimeStamp("2019-02-18 17:36:24", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .startDate(getTimeStamp("2019-11-21 00:00:00", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .endDate(getTimeStamp("2019-11-22 23:59:59", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
            .employeeComment("Kita-Schließzeit")
            .managerComment("Genehmigt")
            .status(VacationStatus.APPROVED)
            .build();
    
    private ContractCondition c1 = new ContractConditionBuilder()
    		.person(p7)
    		.createdAt(getTimeStamp("2019-05-02 07:40:29", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
    		.modifiedAt(getTimeStamp("2019-05-08 09:58:11", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
    		.cutoffDate(getTimeStamp("2019-05-01", "yyyy'-'MM'-'dd"))
    		.cutoffDateEnd(null)
    		.cutoffManhours(0.0f)
    		.cutoffVacationDays(0)
    		.manhoursPerWeek(40.0f)    		    		
    		.manhoursSubtractionPerMonth(0)
    		.traveltimeSubtractionInHours(1.0f)
    		.vacationDaysPerYear(17)
    		.current(true)
    		.build();
    
    private ContractCondition c2 = new ContractConditionBuilder()
    		.person(p6)
    		.createdAt(getTimeStamp("2015-12-23 07:40:29", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
    		.modifiedAt(getTimeStamp("2018-07-12 16:27:33", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
    		.cutoffDate(getTimeStamp("2016-01-01", "yyyy'-'MM'-'dd"))
    		.cutoffDateEnd(null)
    		.cutoffManhours(0.0f)
    		.cutoffVacationDays(0)
    		.manhoursPerWeek(40.0f)		   		
    		.manhoursSubtractionPerMonth(0)
    		.traveltimeSubtractionInHours(1.0f)
    		.vacationDaysPerYear(26)
    		.current(true)
    		.build();

    private ContractCondition c3 = new ContractConditionBuilder()
    		.person(p10)
    		.createdAt(getTimeStamp("2016-01-01 00:00:00", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
    		.modifiedAt(getTimeStamp("2017-04-11 00:00:00", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
    		.cutoffDate(getTimeStamp("2016-01-01", "yyyy'-'MM'-'dd"))
    		.cutoffDateEnd(getTimeStamp("2016-12-31", "yyyy'-'MM'-'dd"))
    		.cutoffManhours(0.0f)
    		.cutoffVacationDays(0)
    		.manhoursPerWeek(38.0f)		   		
    		.manhoursSubtractionPerMonth(0)
    		.traveltimeSubtractionInHours(1.0f)
    		.vacationDaysPerYear(29)
    		.current(false)
    		.build();
    
    private ContractCondition c4 = new ContractConditionBuilder()
    		.person(p10)
    		.createdAt(getTimeStamp("2017-01-01 00:00:00", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
    		.modifiedAt(getTimeStamp("2018-01-03 15:03:15", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
    		.cutoffDate(getTimeStamp("2017-01-01", "yyyy'-'MM'-'dd"))
    		.cutoffDateEnd(getTimeStamp("2017-12-31", "yyyy'-'MM'-'dd"))
    		.cutoffManhours(16.0f)
    		.cutoffVacationDays(0)
    		.manhoursPerWeek(38.0f)		   		
    		.manhoursSubtractionPerMonth(0)
    		.traveltimeSubtractionInHours(1.0f)
    		.vacationDaysPerYear(29)
    		.current(false)
    		.build();
    
    private ContractCondition c5 = new ContractConditionBuilder()
    		.person(p10)
    		.createdAt(getTimeStamp("2018-01-03 15:03:23", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
    		.modifiedAt(getTimeStamp("2019-01-03 13:02:18", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
    		.cutoffDate(getTimeStamp("2018-01-01", "yyyy'-'MM'-'dd"))
    		.cutoffDateEnd(getTimeStamp("2018-12-31", "yyyy'-'MM'-'dd"))
    		.cutoffManhours(4.27f)
    		.cutoffVacationDays(0)
    		.manhoursPerWeek(38.0f)		   		
    		.manhoursSubtractionPerMonth(0)
    		.traveltimeSubtractionInHours(1.0f)
    		.vacationDaysPerYear(30)
    		.current(false)
    		.build();
    
    private ContractCondition c6 = new ContractConditionBuilder()
    		.person(p10)
    		.createdAt(getTimeStamp("2019-01-03 13:02:39", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
    		.modifiedAt(getTimeStamp("2019-03-11 07:01:05", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
    		.cutoffDate(getTimeStamp("2018-01-01", "yyyy'-'MM'-'dd"))
    		.cutoffDateEnd(getTimeStamp("2019-12-31", "yyyy'-'MM'-'dd"))
    		.cutoffManhours(-1.5f)
    		.cutoffVacationDays(0)
    		.manhoursPerWeek(35.0f)		   		
    		.manhoursSubtractionPerMonth(0)
    		.traveltimeSubtractionInHours(1.0f)
    		.vacationDaysPerYear(31)
    		.current(true)
    		.build();
    
    private ContractCondition c7 = new ContractConditionBuilder()
    		.person(p11)
    		.createdAt(getTimeStamp("2018-01-03 16:47:09", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
    		.modifiedAt(getTimeStamp("2018-01-03 16:47:09", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
    		.cutoffDate(getTimeStamp("2016-01-01", "yyyy'-'MM'-'dd"))
    		.cutoffDateEnd(getTimeStamp("2017-12-31", "yyyy'-'MM'-'dd"))
    		.cutoffManhours(8.0f)
    		.cutoffVacationDays(0)
    		.manhoursPerWeek(40.0f)		   		
    		.manhoursSubtractionPerMonth(0)
    		.traveltimeSubtractionInHours(1.0f)
    		.vacationDaysPerYear(26)
    		.current(false)
    		.build();
    
    private ContractCondition c8 = new ContractConditionBuilder()
    		.person(p11)
    		.createdAt(getTimeStamp("2019-03-08 09:24:49", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
    		.modifiedAt(getTimeStamp("2019-03-11 09:24:49", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
    		.cutoffDate(getTimeStamp("2018-01-01", "yyyy'-'MM'-'dd"))
    		.cutoffDateEnd(getTimeStamp("2019-12-31", "yyyy'-'MM'-'dd"))
    		.cutoffManhours(-4.58f)
    		.cutoffVacationDays(0)
    		.manhoursPerWeek(40.0f)		   		
    		.manhoursSubtractionPerMonth(0)
    		.traveltimeSubtractionInHours(1.0f)
    		.vacationDaysPerYear(29)
    		.current(true)
    		.build();
    
    private ContractCondition c9 = new ContractConditionBuilder()
    		.person(p9)
    		.createdAt(getTimeStamp("2019-05-02 07:40:29", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
    		.modifiedAt(getTimeStamp("2019-05-08 09:58:11", "yyyy'-'MM'-'dd' 'HH':'mm':'ss"))
    		.cutoffDate(getTimeStamp("2019-05-01", "yyyy'-'MM'-'dd"))
    		.cutoffDateEnd(null)
    		.cutoffManhours(0.0f)
    		.cutoffVacationDays(0)
    		.manhoursPerWeek(40.0f)    		    		
    		.manhoursSubtractionPerMonth(0)
    		.traveltimeSubtractionInHours(1.0f)
    		.vacationDaysPerYear(26)
    		.current(true)
    		.build();
    
    private Calendar addMinutes(Calendar calendar, int minutes) {
    	Calendar calendarNew = (Calendar)calendar.clone();
    	calendarNew.add(Calendar.MINUTE, minutes);
        return calendarNew;
    }
    
    private Calendar addHours(Calendar calendar, int hours) {
    	Calendar calendarNew = (Calendar)calendar.clone();
    	calendarNew.add(Calendar.HOUR_OF_DAY, hours);
        return calendarNew;
    }
    
    private Calendar addDays(Calendar calendar, int days) {
    	Calendar calendarNew = (Calendar)calendar.clone();
    	calendarNew.add(Calendar.DAY_OF_MONTH, days);
        return calendarNew;
    }
    
    private Calendar getTimeStamp(Calendar calendar) {
    	calendar.set(Calendar.HOUR_OF_DAY, 8);
    	calendar.set(Calendar.MINUTE, 0);
    	calendar.set(Calendar.SECOND, 0);
    	calendar.set(Calendar.MILLISECOND, 0);
    	return calendar;
    }
    
	private Calendar getTimeStamp(String date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
        Calendar calendar = Calendar.getInstance();
        /*calendar.set(Calendar.YEAR, 0);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.WEEK_OF_YEAR, 0);
        calendar.set(Calendar.WEEK_OF_MONTH, 0);
        calendar.set(Calendar.DAY_OF_YEAR, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.DAY_OF_WEEK, 0);
        calendar.set(Calendar.HOUR, 0);
    	calendar.set(Calendar.MINUTE, 0);
    	calendar.set(Calendar.SECOND, 0);
    	calendar.set(Calendar.MILLISECOND, 0);*/
        try {
			calendar.setTime(dateFormat.parse(date));
		} catch (ParseException e) {
			LOG.info("Could not fill Database");
		}
        return calendar;
	}
}
