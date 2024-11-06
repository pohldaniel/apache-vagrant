package de.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "technical_key")
public class TechnicalKey {
	
	@Id
	@Column(name = "primary_key_name", updatable = false, nullable = false)
    private String primaryKeyName;
	
	@Column(name = "current_value", nullable = false)
	private long currentValue;
	
	public String getPrimaryKeyName() {
		return primaryKeyName;
	}
		
	public void setPrimaryKeyName(String primaryKeyName) {
		this.primaryKeyName = primaryKeyName;
	}
	
	public long getCurrentValue() {
		return currentValue;
	}
	
	public void setCurrentValue(long currentValue) {
		this.currentValue = currentValue;
	}
		
	public void update(TechnicalKey technicalKey) {
		this.primaryKeyName = technicalKey.getPrimaryKeyName();
		this.currentValue = technicalKey.getCurrentValue();
	}
	
	public void incrementCurrentValue() {
		this.currentValue++;
	}
}
