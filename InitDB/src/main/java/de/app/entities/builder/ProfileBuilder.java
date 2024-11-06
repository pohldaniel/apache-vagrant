package de.app.entities.builder;

import java.util.Calendar;
import java.util.Locale;

import org.apache.commons.lang3.builder.Builder;

import de.app.entities.Person;
import de.app.entities.Profile;

public class ProfileBuilder implements Builder<Profile> {
	
	private Profile profile;
	
	public ProfileBuilder() {
		this.profile = new Profile();
	}

	public ProfileBuilder id(Person person) {
		this.profile.setId(person);
		return this;
	}
	
	public ProfileBuilder birthday(Calendar birthday) {
		this.profile.setBirthday(birthday);
		return this;
	}
	
	public ProfileBuilder memberSince(Calendar memberSince) {
		this.profile.setMemberSince(memberSince);
		return this;
	}
	
	public ProfileBuilder locale(Locale locale) {
		this.profile.setLocale(locale);
		return this;
	}

	@Override
	public Profile build() {
	     return profile;
	}
}
