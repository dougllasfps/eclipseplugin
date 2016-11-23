package org.dougllas.classgenerator.properties;

public enum MVCDefinitionValue {

	REPOSITORY(1),
	SERVICE(2),
	LDM(3),
	LIST(4),
	FORM(5);
	
	private MVCDefinitionValue(int value) {
		this.value = value;
	}
	
	public static MVCDefinitionValue byValue(int value){
		for (MVCDefinitionValue a : values()){
			if(a.value == value){
				return a;
			}
		}
		
		return null;
	}

	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}