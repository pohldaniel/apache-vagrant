package de.app.entities;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import de.app.entities.enums.Status;
import de.app.utils.CalendarUtils;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "project")
public class Project {
	
	@Id
	@Column(name = "id", unique = true)
	private String id;
	
	@JsonIgnore
	@OneToMany(targetEntity = Timesheet.class, mappedBy = "project", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.SET_NULL)
	private Set<Timesheet> timesheets = new HashSet<>();
	
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "project_person", joinColumns = { @JoinColumn(name = "project_id", referencedColumnName = "id", foreignKey=@ForeignKey(name = "fk_project_person")) }, inverseJoinColumns = { @JoinColumn(name = "person_id", referencedColumnName = "id", updatable = true, foreignKey=@ForeignKey(name = "fk_person_person")) })
	private Set<Person> persons;
	
	@JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", columnDefinition = "DATE DEFAULT NULL")
    private Calendar startDate;
	
	@JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "end_date", columnDefinition = "DATE DEFAULT NULL")
    private Calendar endDate;
	
	@JsonFormat(pattern="yyyy-MM-dd' 'HH:mm:ss")
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdAt = CalendarUtils.CalendarFrom(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
	
	@JsonFormat(pattern="yyyy-MM-dd' 'HH:mm:ss")
	@Column(name = "modified_at", nullable = true, columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar modifiedAt;
	
	@Column(name = "location")
	private String location;

	@Column(name = "company")
	private String company;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setTimesheets(Set<Timesheet> timesheets) {
		this.timesheets = timesheets;
	}
	
	public Set<Timesheet> getTimesheets() {
		return timesheets;
	}
	
	public Set<Person> getPersons() {
		return persons;
	}
	
	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
	
	public void addPersons(Person... persons) {
		
		if(this.persons == null)
			this.persons = new HashSet<Person>();
		
		for (Person person : persons) {
			this.persons.add(person);
		}
	}
	
	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public Calendar getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Calendar modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void update(Project project) {
		this.startDate = project.getStartDate();
		this.endDate = project.getEndDate();
		this.createdAt = project.getCreatedAt();
		this.modifiedAt = project.getModifiedAt();
		this.location = project.getLocation();
		this.company = project.getCompany();
		this.status = project.getStatus();
	}
}
