package de.app.entities.builder;

import org.apache.commons.lang3.builder.Builder;
import de.app.entities.TechnicalKey;

public class TechnicalKeyBuilder implements Builder<TechnicalKey> {
	
	private TechnicalKey technicalKey;
	
    public TechnicalKeyBuilder() { 	
    	technicalKey = new TechnicalKey();
    }
    
	public TechnicalKeyBuilder primaryKeyName(String primaryKeyName) {
		this.technicalKey.setPrimaryKeyName(primaryKeyName);
		return this;
	}

	public TechnicalKeyBuilder currentValue(long currentValue) {
		this.technicalKey.setCurrentValue(currentValue);
		return this;
	}
	
	@Override
	public TechnicalKey build() {
        return technicalKey;
    }
}
