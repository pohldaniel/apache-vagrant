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
@Table(name = "address")
public class Address {
	
	@Id
	@OneToOne(targetEntity = Person.class, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="id", referencedColumnName="id", updatable = false, nullable = false, foreignKey=@ForeignKey(name = "fk_person_adress"))	
    private Person id;
	
	@Column(name = "street")
	private String street;
	
	@ColumnDefault(value = "NULL")
	@Column(name = "house_number", nullable = true, length = 255)
	private String houseNumber;
	
	@ColumnDefault(value = "NULL")
	@Column(name = "zip_code", nullable = true, length = 255)
	private String zipCode;
	
	@Column(name = "city")
	private String city;

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return  Objects.equals(id.getId(), address.id.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.getId(), street, houseNumber, zipCode, city);
    }
	
	public Person getId() {
		return id;
	}

	public void setId(Person id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public void update(Address address) {
		this.street = address.getStreet();
		this.houseNumber = address.getHouseNumber();
		this.zipCode = address.getZipCode();
		this.city = address.getCity();
	}
}
