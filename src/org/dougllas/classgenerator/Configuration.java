package org.dougllas.classgenerator;

import java.util.Collection;

import org.dougllas.classgenerator.properties.ClasseConfig;

public interface Configuration {

	String getGroupid();

	String getModel();
	
	String getService();
	
	String getLdm();
	
	String getCreateMode();

	String[] getClasses();
	
	Collection<ClasseConfig> getClassConfigurations();
	
	String getProjectLocation();

	String getRepository();
	
	String getBeans();
	
}