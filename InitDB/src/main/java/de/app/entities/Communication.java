package de.app.entities;

import java.util.Objects;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "communication")
public class Communication {
	
	@Id
	@OneToOne(targetEntity = Person.class, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="id", referencedColumnName="id", updatable = false, nullable = false, foreignKey=@ForeignKey(name = "fk_person_communication"))	
    private Person id;
	
	@Column(name = "email")
	private String email;
    
	@ColumnDefault(value = "NULL")
	@Column(name = "phone_private", nullable = true, length = 255)
    private String phonePrivate;
    
	@ColumnDefault(value = "NULL")
    @Column(name = "phone_work", nullable = true, length = 255)
    private String phoneWork;

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Communication communication = (Communication) o;
        return  Objects.equals(id.getId(), communication.id.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.getId(), email, phonePrivate, phoneWork);
    }
	
	public Person getId() {
		return id;
	}

	public void setId(Person id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonePrivate() {
		return phonePrivate;
	}

	public void setPhonePrivate(String phonePrivate) {
		this.phonePrivate = phonePrivate;
	}

	public String getPhoneWork() {
		return phoneWork;
	}

	public void setPhoneWork(String phoneWork) {
		this.phoneWork = phoneWork;
	}
	
	public void update(Communication communication) {
		this.email = communication.getEmail();
		this.phonePrivate = communication.getPhonePrivate();
		this.phoneWork = communication.getPhoneWork();		
	}
}
