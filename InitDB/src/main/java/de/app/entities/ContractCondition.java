package de.app.entities;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Objects;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import de.app.utils.CalendarUtils;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "contract_condition")
public class ContractCondition {

	private static final float DEFAULT_CUTOFF_MANHOURS = 0.0f;
	private static final int DEFAULT_CUTOFF_VACATION_DAYS = 0;
	private static final float DEFAULT_MANHOURS_PER_WEEK = 40.0f;
	private static final int DEFAULT_MANHOURS_SUBTRACTION_PER_MONTH = 0;
	private static final float DEFAULT_TRAVELTIME_SUBTRACTION_HOURS = 1.0f;
	private static final int DEFAULT_VACATION_DAYS_PER_YEAR = 26;
	
	@Id
	@TableGenerator(name="contract_condition_id_generator", table="technical_key", pkColumnName = "primary_key_name", valueColumnName = "current_value", pkColumnValue = "contract_condition_id", allocationSize = 1)		
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "contract_condition_id_generator")	
	private Long id;
	
	@JsonIgnore
	@ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
	@JoinColumn(name="person_id", referencedColumnName="id", nullable = true, foreignKey=@ForeignKey(name = "fk_person_contract_condition"))
    private Person person;
	
	@JsonIgnore
	@Column(name = "assigned_person")
	private String assignedPerson;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
    @Column(name = "cutoff_date", columnDefinition = "DATE DEFAULT (CURRENT_DATE)")
    private Calendar cutoffDate = CalendarUtils.CalendarFrom(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
	
	@ColumnDefault(value = "0.0")
	@Column(name = "cutoff_manhours", nullable = false)
	private float cutoffManhours;
	
	@ColumnDefault(value = "0")
	@Column(name = "cutoff_vacation_days", nullable = false)
	private int cutoffVacationDays;
	
	@ColumnDefault(value = "40.0")
	@Column(name = "manhours_per_week", nullable = false)
	private float manhoursPerWeek;
	
	@ColumnDefault(value = "0")
	@Column(name = "manhours_subtraction_per_month", nullable = false)
	private int manhoursSubtractionPerMonth;
	
	@ColumnDefault(value = "1.0")
	@Column(name = "traveltime_subtraction_in_hours", nullable = false)
	private float traveltimeSubtractionInHours;
	
	@ColumnDefault(value = "26")
	@Column(name = "vacation_days_per_year", nullable = false)
	private int vacationDaysPerYear;
	
	@JsonFormat(pattern="yyyy-MM-dd' 'HH:mm:ss")
	@Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdAt = CalendarUtils.CalendarFrom(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

    @JsonFormat(pattern="yyyy-MM-dd' 'HH:mm:ss")
    @Column(name = "modified_at", nullable = true, columnDefinition = "TIMESTAMP")
    @ColumnDefault(value = "NULL")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifiedAt;
    
    @JsonFormat(pattern="yyyy-MM-dd") 
    @Column(name = "cutoff_date_end", nullable = true)
    @ColumnDefault(value = "NULL")
    @Temporal(TemporalType.DATE)
    private Calendar cutoffDateEnd;
    
    @ColumnDefault(value = "false")
	@Column(name = "current", nullable = false)
	private Boolean current;
	
	public ContractCondition(){
    	 cutoffDate = CalendarUtils.CalendarFrom(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    	 cutoffManhours = DEFAULT_CUTOFF_MANHOURS;
    	 cutoffVacationDays = DEFAULT_CUTOFF_VACATION_DAYS;
    	 manhoursPerWeek = DEFAULT_MANHOURS_PER_WEEK;
    	 manhoursSubtractionPerMonth = DEFAULT_MANHOURS_SUBTRACTION_PER_MONTH;
    	 traveltimeSubtractionInHours = DEFAULT_TRAVELTIME_SUBTRACTION_HOURS;
    	 vacationDaysPerYear = DEFAULT_VACATION_DAYS_PER_YEAR;
    	 createdAt = CalendarUtils.CalendarFrom(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    	 modifiedAt = null;
    	 cutoffDateEnd = null;
    	 current = false;
    }
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractCondition contractCondition = (ContractCondition) o;
        return  Objects.equals(id, contractCondition.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, 
        		assignedPerson, 
        		cutoffDate, 
        		cutoffManhours, 
        		cutoffVacationDays, 
        		manhoursPerWeek, 
        		manhoursSubtractionPerMonth, 
        		traveltimeSubtractionInHours, 
        		vacationDaysPerYear,
        		createdAt,
        		modifiedAt,
        		cutoffDateEnd,
        		current);
    }
    
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
	
	 public Calendar getCutoffDate() {
		return cutoffDate;
	}

	public void setCutoffDate(Calendar cutoffDate) {
		this.cutoffDate = cutoffDate;
	}

	public float getCutoffManhours() {
		return cutoffManhours;
	}

	public void setCutoffManhours(float cutoffManhours) {
		this.cutoffManhours = cutoffManhours;
	}

	public int getCutoffVacationDays() {
		return cutoffVacationDays;
	}

	public void setCutoffVacationDays(int cutoffVacationDays) {
		this.cutoffVacationDays = cutoffVacationDays;
	}

	public float getManhoursPerWeek() {
		return manhoursPerWeek;
	}

	public void setManhoursPerWeek(float manhoursPerWeek) {
		this.manhoursPerWeek = manhoursPerWeek;
	}

	public int getManhoursSubtractionPerMonth() {
		return manhoursSubtractionPerMonth;
	}

	public void setManhoursSubtractionPerMonth(int manhoursSubtractionPerMonth) {
		this.manhoursSubtractionPerMonth = manhoursSubtractionPerMonth;
	}

	public float getTraveltimeSubtractionInHours() {
		return traveltimeSubtractionInHours;
	}

	public void setTraveltimeSubtractionInHours(float traveltimeSubtractionInHours) {
			this.traveltimeSubtractionInHours = traveltimeSubtractionInHours;
	}

	public int getVacationDaysPerYear() {
		return vacationDaysPerYear;
	}

	public void setVacationDaysPerYear(int vacationDaysPerYear) {
		this.vacationDaysPerYear = vacationDaysPerYear;
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

	public Calendar getCutoffDateEnd() {
		return cutoffDateEnd;
	}

	public void setCutoffDateEnd(Calendar cutoffDateEnd) {
		this.cutoffDateEnd = cutoffDateEnd;
	}
		
	public Boolean getCurrent() {
		return current;
	}

	public void setCurrent(Boolean current) {
		this.current = current;
	}
		
	public void update(ContractCondition contractCondition) {
		this.person = contractCondition.getPerson();
		this.cutoffDate = contractCondition.getCutoffDate();
		this.cutoffManhours = contractCondition.getCutoffManhours();
		this.cutoffVacationDays = contractCondition.getCutoffVacationDays();
		this.manhoursPerWeek = contractCondition.getManhoursPerWeek();
		this.manhoursSubtractionPerMonth = contractCondition.getManhoursSubtractionPerMonth();
		this.traveltimeSubtractionInHours = contractCondition.getTraveltimeSubtractionInHours();
		this.vacationDaysPerYear = contractCondition.getVacationDaysPerYear();
		this.createdAt = contractCondition.getCreatedAt();
		this.modifiedAt = contractCondition.getModifiedAt();
		this.cutoffDateEnd = contractCondition.getCutoffDateEnd();
		this.current = contractCondition.getCurrent();
		
		this.assignedPerson = person.getId();
	}
}
