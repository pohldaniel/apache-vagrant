package de.app.entities.builder;

import org.apache.commons.lang3.builder.Builder;

import de.app.entities.Communication;
import de.app.entities.Person;

public class CommunicationBuilder  implements Builder<Communication> {
	
	private Communication communication;
	
	public CommunicationBuilder() {
		this.communication = new Communication();
	}
	
	public CommunicationBuilder id(Person person) {
		this.communication.setId(person);
		return this;
	}
	
	public CommunicationBuilder email(String email) {
		this.communication.setEmail(email);
		return this;
	}
	
	public CommunicationBuilder phonePrivate(String phonePrivate) {
		this.communication.setPhonePrivate(phonePrivate);
		return this;
	}
	
	public CommunicationBuilder phoneWork(String phoneWork) {
		this.communication.setPhoneWork(phoneWork);
		return this;
	}
	
	@Override
	public Communication build() {
	     return communication;
	}
}
