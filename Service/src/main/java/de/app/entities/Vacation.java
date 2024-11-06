package de.app.entities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.LinkedHashMap;

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
import jakarta.persistence.Transient;

import de.app.entities.enums.VacationStatus;
import de.app.utils.CalendarUtils;

@Entity
@Table(name = "vacation")
public class Vacation {

	@Id
	@TableGenerator(name="vacation_id_generator", table="technical_key", pkColumnName = "primary_key_name", valueColumnName = "current_value", pkColumnValue = "vacation_id", allocationSize = 1)		
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "vacation_id_generator")	
	private Long id;
	
	@JsonIgnore
	@ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
	@JoinColumn(name="person_id", referencedColumnName="id", nullable = true, foreignKey=@ForeignKey(name = "fk_person_vacation"))
    private Person person;
	
	@JsonIgnore
	@Column(name = "assigned_person")
	private String assignedPerson;
	
	@JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Calendar startDate;
	
	@JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Calendar endDate;
    
    @JsonFormat(pattern="yyyy-MM-dd' 'HH:mm:ss")
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdAt = CalendarUtils.CalendarFrom(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

    @JsonFormat(pattern="yyyy-MM-dd' 'HH:mm:ss")
	@Column(name = "modified_at", nullable = true, columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar modifiedAt;
    
    @ColumnDefault(value = "0")
	@Column(name = "actual_vacation_days", nullable = false)
	private int actualVacationDays = 0;
    
    @Column(name = "employee_comment", length=255)
	private String employeeComment;

	@Column(name = "manager_comment", length=255)
	private String managerComment;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private VacationStatus status;
	
	@Transient
	private LinkedHashMap<String, Timesheet> timesheetMap;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public int getActualVacationDays() {
		return actualVacationDays;
	}

	public void setActualVacationDays(int actualVacationDays) {
		this.actualVacationDays = actualVacationDays;
	}
	
	public String getEmployeeComment() {
		return employeeComment;
	}

	public void setEmployeeComment(String employeeComment) {
		this.employeeComment = employeeComment;
	}

	public String getManagerComment() {
		return managerComment;
	}

	public void setManagerComment(String managerComment) {
		this.managerComment = managerComment;
	}
	
	public VacationStatus getStatus() {
		return status;
	}

	public void setStatus(VacationStatus status) {
		this.status = status;
	}
	
	public LinkedHashMap<String, Timesheet> getTimesheetMap() {
		return timesheetMap;
	}

	public void setTimesheetMap(LinkedHashMap<String, Timesheet> timesheetMap) {
		this.timesheetMap = timesheetMap;
	}
	
	public void update(Vacation vacation) {
		this.person = vacation.getPerson();
		this.startDate = vacation.getStartDate();
		this.endDate = vacation.getEndDate();
		this.createdAt = vacation.getCreatedAt();
		this.modifiedAt = vacation.getModifiedAt();	
		this.employeeComment = vacation.getEmployeeComment();
		this.managerComment = vacation.getManagerComment();
		this.status = vacation.getStatus();		
		updateVacationDays();
		this.assignedPerson = person.getId();
	}
	
	public void updateVacationDays() {
		actualVacationDays = (int)Duration.between(this.startDate.toInstant(), this.endDate.toInstant()).toDays();
	}
}
