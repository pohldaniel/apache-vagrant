package de.app.entities.builder;

import java.util.Calendar;
import java.util.Set;

import org.apache.commons.lang3.builder.Builder;

import de.app.entities.Person;
import de.app.entities.Project;
import de.app.entities.Timesheet;
import de.app.entities.enums.Status;

public class ProjectBuilder  implements Builder<Project> {
	
	private Project project;
	
	public ProjectBuilder() {
		this.project = new Project();
	}
	
	public ProjectBuilder id(String id) {
		this.project.setId(id);
		return this;
	}
	
	public ProjectBuilder persons(Set<Person> persons) {
		this.project.setPersons(persons);
		return this;
	}
	
	public ProjectBuilder persons(Person... persons) {
		this.project.addPersons(persons);
		return this;
	}
		
	public ProjectBuilder timesheets(Set<Timesheet> timesheets) {
		this.project.setTimesheets(timesheets);
		return this;
	}
	
	public ProjectBuilder startDate(Calendar startDate) {
		this.project.setStartDate(startDate);
		return this;
	}
	
	public ProjectBuilder endDate(Calendar endDate) {
		this.project.setEndDate(endDate);
		return this;
	}
	
	public ProjectBuilder createdAt(Calendar createdAt) {
		this.project.setCreatedAt(createdAt);
		return this;
	}
	
	public ProjectBuilder modifiedAt(Calendar modifiedAt) {
		this.project.setModifiedAt(modifiedAt);
		return this;
	}
	
	public ProjectBuilder location(String location) {
		this.project.setLocation(location);
		return this;
	}
	
	public ProjectBuilder company(String company) {
		this.project.setCompany(company);
		return this;
	}
	
	public ProjectBuilder status(Status status) {
		this.project.setStatus(status);
		return this;
	}
	
	@Override
	public Project build() {
	     return project;
	}
}
