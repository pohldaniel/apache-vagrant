package de.app.entities;

import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "profile")
public class Profile {
	
	@Id
	@OneToOne(targetEntity = Person.class, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="id", referencedColumnName="id", updatable = false, nullable = false, foreignKey=@ForeignKey(name = "fk_person_profile"))	
    private Person id;
	
	@JsonFormat(pattern="yyyy-MM-dd") 
    @Column(name = "birthday", nullable = true)
	@Temporal(TemporalType.DATE)
    private Calendar birthday;
	
	@JsonFormat(pattern="yyyy-MM-dd' 'HH:mm:ss")
	@ColumnDefault(value = "NULL")
    @Column(name = "member_since", nullable = true, columnDefinition = "TIMESTAMP")	
	@Temporal(TemporalType.TIMESTAMP)
    private Calendar memberSince;
	
	@Column(name = "locale", nullable = true)	
	private Locale locale;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return  Objects.equals(id.getId(), profile.id.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.getId(), birthday, memberSince, locale);
    }
	
	public Person getId() {
		return id;
	}

	public void setId(Person id) {
		this.id = id;
	}
	
	public Calendar getBirthday() {
		return birthday;
	}

	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}
	
	public Calendar getMemberSince() {
		return memberSince;
	}

	public void setMemberSince(Calendar memberSince) {
		this.memberSince = memberSince;
	}
	
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	public void update(Profile profile) {
		this.birthday = profile.getBirthday();
		this.memberSince = profile.getMemberSince();
		this.locale = profile.getLocale();
	}
}
