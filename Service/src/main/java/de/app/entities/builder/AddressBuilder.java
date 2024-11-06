package de.app.entities.builder;

import org.apache.commons.lang3.builder.Builder;

import de.app.entities.Address;
import de.app.entities.Person;

public class AddressBuilder implements Builder<Address> {
	
	private Address address;
	
	public AddressBuilder() {
		this.address = new Address();
	}

	public AddressBuilder id(Person person) {
		this.address.setId(person);
		return this;
	}
	
	public AddressBuilder street(String street) {
		this.address.setStreet(street);
		return this;
	}
	
	public AddressBuilder houseNumber(String houseNumber) {
		this.address.setHouseNumber(houseNumber);
		return this;
	}
	
	public AddressBuilder zipCode(String zipCode) {
		this.address.setZipCode(zipCode);
		return this;
	}
	
	public AddressBuilder city(String city) {
		this.address.setCity(city);
		return this;
	}
	
	@Override
	public Address build() {
	     return address;
	}
}
