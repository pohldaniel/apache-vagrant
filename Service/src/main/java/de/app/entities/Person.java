package de.app.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import de.app.entities.enums.Role;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "prename")
    private String prename;

    @Column(name = "mail")
    private String mail;

    @Column(name = "external_company")
    private String externalCompany;

    @JsonIgnore
    @Column(name = "password_hash")
    private String passwordHash;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "person_id"))
    @Column(name = "role", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Column(name = "password_reset_token")
    private String passwordResetToken;

    @JsonFormat(pattern="yyyy-MM-dd' 'HH:mm:ss") 
    @Column(name = "password_reset_token_expiry_date", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar passwordResetTokenExpiryDate;

    @JsonIgnore
    @OneToMany(targetEntity = Vacation.class, mappedBy = "person", fetch = FetchType.LAZY)	
	@Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.SET_NULL)
	private Set<Vacation> vacations = new HashSet<>();
    
    @JsonIgnore
    @OneToMany(targetEntity = ContractCondition.class, mappedBy = "person", fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.SET_NULL)
	private Set<ContractCondition> contractConditions = new HashSet<>();
    
    @JsonIgnore
    @OneToMany(targetEntity = Timesheet.class, mappedBy = "person", fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.SET_NULL)
	private Set<Timesheet> timesheets = new HashSet<>();
    
    @JsonIgnore
    @ManyToMany(targetEntity = Project.class, mappedBy = "persons", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Project> projects = new HashSet<>();
    
    @OneToOne(targetEntity = Profile.class, mappedBy = "id", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Profile profile;
    
    @OneToOne(targetEntity = Address.class, mappedBy = "id", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Address address;
    
    @OneToOne(targetEntity = Communication.class, mappedBy = "id", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Communication communication;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return  Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, prename, mail, externalCompany, roles);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", surname='" + surname + '\'' +
                ", prename='" + prename + '\'' +
                ", mail='" + mail + '\'' +
                ", externalCompany='" + externalCompany + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", role=" + roles +
                '}';
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPrename() {
		return prename;
	}

	public void setPrename(String prename) {
		this.prename = prename;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getExternalCompany() {
		return externalCompany;
	}

	public void setExternalCompany(String externalCompany) {
		this.externalCompany = externalCompany;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void addRoles(Role... roles) {
		
		if(this.roles == null)
			this.roles = new HashSet<Role>();
		
		for (Role role : roles) {
			this.roles.add(role);
		}
	}

	public String getPasswordResetToken() {
		return passwordResetToken;
	}

	public void setPasswordResetToken(String passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}

	public Calendar getPasswordResetTokenExpiryDate() {
		return passwordResetTokenExpiryDate;
	}

	public void setPasswordResetTokenExpiryDate(Calendar passwordResetTokenExpiryDate) {
		if(passwordResetTokenExpiryDate != null)
			passwordResetTokenExpiryDate.set(Calendar.MILLISECOND, 0);
		this.passwordResetTokenExpiryDate = passwordResetTokenExpiryDate;
	}

	public void setVacations(Set<Vacation> vacations) {
		this.vacations = vacations;
	}
	
	public Set<Vacation> getVacations() {
		return vacations;
	}
	
	public void setContractConditions(Set<ContractCondition> contractConditions) {
		this.contractConditions = contractConditions;
	}
	
	public Set<ContractCondition> getContractConditions() {
		return contractConditions;
	}
	
	public void setTimesheets(Set<Timesheet> timesheets) {
		this.timesheets = timesheets;
	}
	
	public Set<Timesheet> getTimesheets() {
		return timesheets;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Communication getCommunication() {
		return communication;
	}

	public void setCommunication(Communication communication) {
		this.communication = communication;
	}
    
	public void update(Person person) {
		this.surname = person.getSurname();
		this.prename = person.getPrename();
		this.mail = person.getMail();
		
		this.externalCompany = person.getExternalCompany();
		this.passwordHash = person.getPasswordHash();
		this.roles = person.getRoles();

		this.passwordResetToken = person.getPasswordResetToken();
		this.passwordResetTokenExpiryDate = person.getPasswordResetTokenExpiryDate();
		this.profile = person.getProfile();
		this.address = person.getAddress();
		this.communication = person.getCommunication();
	}
    
}
