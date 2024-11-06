package de.app.entities.builder;

import java.util.Calendar;

import org.apache.commons.lang3.builder.Builder;

import de.app.entities.Person;
import de.app.entities.Project;
import de.app.entities.Timesheet;
import de.app.entities.enums.Category;

public class TimesheetBuilder implements Builder<Timesheet> {
	
	private Timesheet timesheet;
	
	public TimesheetBuilder() {		
		timesheet = new Timesheet();
	}
	
	public TimesheetBuilder id(Long id) {
		this.timesheet.setId(id);
		return this;
	}
	
	public TimesheetBuilder person(Person person) {
		this.timesheet.setPerson(person);
		return this;
	}
	
	public TimesheetBuilder project(Project project) {
		this.timesheet.setProject(project);
		return this;
	}
	
	public TimesheetBuilder assignedPerson(String assignedPerson) {
		this.timesheet.setAssignedPerson(assignedPerson);
		return this;
	}
	
	public TimesheetBuilder assignedProject(String assignedProject) {
		this.timesheet.setAssignedProject(assignedProject);
		return this;
	}
	
	public TimesheetBuilder startTime(Calendar startTime) {
		this.timesheet.setStartTime(startTime);
		return this;
	}
	
	public TimesheetBuilder endTime(Calendar endTime) {
		this.timesheet.setEndTime(endTime);
		return this;
	}
	
	public TimesheetBuilder category(Category category) {
		this.timesheet.setCategory(category);
		return this;
	}
	
	public TimesheetBuilder task(String task) {
		this.timesheet.setTask(task);
		return this;
	}
	
	public TimesheetBuilder difference(int difference) {
		this.timesheet.setDifference(difference);
		return this;
	}
	
	public TimesheetBuilder summary(int summary) {
		this.timesheet.setSummary(summary);
		return this;
	}

	@Override
	public Timesheet build() {	
		return timesheet;
	}
}
