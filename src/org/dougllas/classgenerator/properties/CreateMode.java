package org.dougllas.classgenerator.properties;

public enum CreateMode {
	
	INCLUDE,
	EXCLUDE;
	
	public static CreateMode byString(String string){
		
		if(string == null){
			return null;
		}
		
		for (CreateMode mode : values()){
			if(mode.name().equalsIgnoreCase(string.trim())){
				return mode;
			}
		}
		
		return null;
	}
}
