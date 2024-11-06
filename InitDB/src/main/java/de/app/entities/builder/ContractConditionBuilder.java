package de.app.entities.builder;

import java.util.Calendar;

import org.apache.commons.lang3.builder.Builder;

import de.app.entities.ContractCondition;
import de.app.entities.Person;

public class ContractConditionBuilder implements Builder<ContractCondition> {
	private ContractCondition contractCondition;
	
	public ContractConditionBuilder() {
		this.contractCondition = new ContractCondition();
	}

	public ContractConditionBuilder id(Long id) {
		this.contractCondition.setId(id);
		return this;
	}
	
	public ContractConditionBuilder person(Person person) {
		this.contractCondition.setPerson(person);
		return this;
	}
	
	public ContractConditionBuilder cutoffDate(Calendar cutoffDate) {
		this.contractCondition.setCutoffDate(cutoffDate);
		return this;
	}
	
	public ContractConditionBuilder cutoffManhours(float cutoffManhours) {
		this.contractCondition.setCutoffManhours(cutoffManhours);
		return this;
	}
	
	public ContractConditionBuilder cutoffVacationDays(int cutoffVacationDays) {
		this.contractCondition.setCutoffVacationDays(cutoffVacationDays);
		return this;
	}
	
	public ContractConditionBuilder manhoursPerWeek(float manhoursPerWeek) {
		this.contractCondition.setManhoursPerWeek(manhoursPerWeek);
		return this;
	}
	
	public ContractConditionBuilder manhoursSubtractionPerMonth(int manhoursSubtractionPerMonth) {
		this.contractCondition.setManhoursSubtractionPerMonth(manhoursSubtractionPerMonth);
		return this;
	}
	
	public ContractConditionBuilder traveltimeSubtractionInHours(int traveltimeSubtractionInHours) {
		this.contractCondition.setTraveltimeSubtractionInHours(traveltimeSubtractionInHours);
		return this;
	}
	
	public ContractConditionBuilder traveltimeSubtractionInHours(float traveltimeSubtractionInHours) {
		this.contractCondition.setTraveltimeSubtractionInHours(traveltimeSubtractionInHours);
		return this;
	}
	
	public ContractConditionBuilder vacationDaysPerYear(int vacationDaysPerYear) {
		this.contractCondition.setVacationDaysPerYear(vacationDaysPerYear);
		return this;
	}
	
	public ContractConditionBuilder createdAt(Calendar createdAt) {
		this.contractCondition.setCreatedAt(createdAt);
		return this;
	}
	
	public ContractConditionBuilder modifiedAt(Calendar modifiedAt) {
		this.contractCondition.setModifiedAt(modifiedAt);
		return this;
	}
	
	public ContractConditionBuilder cutoffDateEnd(Calendar cutoffDateEnd) {
		this.contractCondition.setCutoffDateEnd(cutoffDateEnd);
		return this;
	}

	public ContractConditionBuilder current(Boolean current) {
		this.contractCondition.setCurrent(current);
		return this;
	}
	
	@Override
	public ContractCondition build() {
	     return contractCondition;
	}
}
