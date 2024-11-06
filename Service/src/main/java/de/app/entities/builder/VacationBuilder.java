package de.app.entities.builder;

import java.util.Calendar;

import org.apache.commons.lang3.builder.Builder;

import de.app.entities.Person;
import de.app.entities.Vacation;
import de.app.entities.enums.VacationStatus;

public class VacationBuilder  implements Builder<Vacation>{
	
	private Vacation vacation;
	
    public VacationBuilder() { 	
    	vacation = new Vacation();
    }
    
    public VacationBuilder id(Long id) {
		this.vacation.setId(id);
		return this;
	}
    
    public VacationBuilder person(Person person) {
		this.vacation.setPerson(person);
		return this;
	}
    
    public VacationBuilder startDate(Calendar startDate) {
		this.vacation.setStartDate(startDate);
		return this;
	}
    
    public VacationBuilder endDate(Calendar endDate) {
  		this.vacation.setEndDate(endDate);
  		return this;
  	}
    
    public VacationBuilder createdAt(Calendar createdAt) {
  		this.vacation.setCreatedAt(createdAt);
  		return this;
  	}
    
    public VacationBuilder modifiedAt(Calendar modifiedAt) {
  		this.vacation.setModifiedAt(modifiedAt);
  		return this;
  	}
    
    public VacationBuilder employeeComment(String employeeComment) {
  		this.vacation.setEmployeeComment(employeeComment);
  		return this;
  	}
    
    public VacationBuilder managerComment(String managerComment) {
  		this.vacation.setManagerComment(managerComment);
  		return this;
  	}
    
    public VacationBuilder status(VacationStatus status) {
  		this.vacation.setStatus(status);
  		return this;
  	}
    
    @Override
	public Vacation build() {
        return vacation;
    }
}
