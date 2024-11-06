package de.app.entities;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import de.app.entities.enums.Category;

@Entity
@Table(name = "timesheet")
public class Timesheet {

	@Id
	@TableGenerator(name="timesheet_id_generator", table="technical_key", pkColumnName = "primary_key_name", valueColumnName = "current_value", pkColumnValue = "timesheet_id", allocationSize = 1)		
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "timesheet_id_generator")	
	private Long id;
	
	@ManyToOne(targetEntity = Project.class, fetch = FetchType.EAGER)
	@JoinColumn(name="project_id", referencedColumnName="id", unique = false, foreignKey=@ForeignKey(name = "fk_project_timesheet"))	
	private Project project;
	
	@JsonIgnore
	@ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
	@JoinColumn(name="person_id", referencedColumnName="id", nullable = true, foreignKey=@ForeignKey(name = "fk_person_timesheet"))
	private Person person;
	
	@JsonIgnore
	@Column(name = "assigned_person")
	private String assignedPerson;
	
	@JsonIgnore
	@ColumnDefault(value = "''")
	@Column(name = "assigned_project")
	private String assignedProject;
	
	@JsonFormat(pattern="yyyy-MM-dd' 'HH:mm:ss")  
    @Column(name = "start_time", nullable = false, columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
    private Calendar startTime;
	
	@JsonFormat(pattern="yyyy-MM-dd' 'HH:mm:ss") 
    @Column(name = "end_time", nullable = true, columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
    private Calendar endTime;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "category")
	private Category category;
	
	@Column(name = "task")
	private String task;
	
	@ColumnDefault(value = "0")
	@Column(name = "summary", nullable = false)
	private int summary = 0;
	
	@ColumnDefault(value = "0")
	@Column(name = "difference", nullable = false)
	private int difference = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		if(project == null)
			return;
		this.assignedProject = project.getId();
		this.project = project;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.assignedPerson = person.getId();
		this.person = person;
	}
	
	public String getAssignedPerson() {
		return assignedPerson;
	}
	
	public void setAssignedPerson(String assignedPerson) {
		this.assignedPerson = assignedPerson;
	}
	
	public String getAssignedProject() {
		return assignedProject;
	}
	
	public void setAssignedProject(String assignedProject) {
		this.assignedProject = assignedProject;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public int getDifference() {
		return difference;
	}

	public void setDifference(int difference) {
		this.difference = difference;
	}
	
	public int getSummary() {
		return summary;
	}

	public void setSummary(int summary) {
		this.summary = summary;
	}
	
	public void update(Timesheet timesheet) {
		setPerson(timesheet.getPerson());
		this.startTime = timesheet.getStartTime();
		this.endTime = timesheet.getEndTime();
		this.category = timesheet.getCategory();	
		this.task = timesheet.getTask();
		updateDifference();
		this.summary = timesheet.getSummary();		
		setProject(timesheet.getProject());
	}
	
	public void updateDifference() {
		if(this.category == Category.WORKTIME) {
			long duration = Math.abs(endTime.getTime().getTime() - startTime.getTime().getTime());		
			this.difference = (int)TimeUnit.MILLISECONDS.toMinutes(duration);
		}else if(this.category == Category.TRAVELTIME) {
			long duration = Math.abs(endTime.getTime().getTime() - startTime.getTime().getTime());		
			this.difference = (int)TimeUnit.MILLISECONDS.toMinutes(duration);
		}else if(this.category == Category.VACATION || this.category == Category.ILLNESS) {
			this.difference = 480;
		}
	}
}
