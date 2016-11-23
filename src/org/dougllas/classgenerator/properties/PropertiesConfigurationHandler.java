package org.dougllas.classgenerator.properties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class PropertiesConfigurationHandler {

	private Properties prop;
	private CreateMode mode;
	public static final String 
				project_location = "project_location",
				groupid="groupid",
				artifactid="artifactid",
				model="model",
				service="service",
				repository ="repository",
				ldm="ldm",
				create_mode="create_mode",
				classes="classes",
				beans = "beans";
	
	public PropertiesConfigurationHandler(final String resource) throws IOException, IllegalStateException{
		this.prop = new Properties();
		prop.load(getClass().getResourceAsStream(resource));
		
		String createMode = getProperty(create_mode);
		mode = CreateMode.byString(createMode);
		
		if(mode == null){
			throw new IllegalStateException();
		}
	}
	
	public Properties getProp() {
		return prop;
	}
	
	public PropertiesConfiguration getConfiguration() throws IllegalStateException{
		PropertiesConfiguration config = new PropertiesConfiguration();
		
		config.setArtifactid(getProperty(artifactid));
		config.setCreateMode(getProperty(create_mode));
		config.setGroupid(getProperty(groupid));
		config.setLdm(getProperty(ldm));
		config.setModel(getProperty(model));
		config.setService(getProperty(service));
		config.setClassConfigurations( extractAll( getProperty(classes)) );
		config.setProjectLocation(getProperty(project_location));
		config.setRepository(getProperty(repository));
		config.setBeans(getProperty(beans));
		
		return config;
	}
	
	public Collection<ClasseConfig> extractAll(String value) throws IllegalStateException{
		Collection<ClasseConfig> list = new ArrayList<>();
	
		String[] split = value.split(",");
		
		for (String string : split) {
			ClasseConfig extract = extract(string);
			list.add(extract);
		}
		
		return list;
	}
	
	public ClasseConfig extract(String value) throws IllegalStateException{
		
		boolean isExclude = mode.equals(CreateMode.EXCLUDE);

		String[] createConfiguration = getCreateConfiguration(value);
		
		ClasseConfig config = new ClasseConfig();
		config.setClassName(extractClassName(value));
		
		if(createConfiguration == null){
			if(isExclude){
				config.setNone(true);
			}else{
				config.setAll(true);
			}
		}else{
			for( final String cc : createConfiguration ){
				
				Integer ccValue = Integer.valueOf(cc);
				MVCDefinitionValue byValue = MVCDefinitionValue.byValue(ccValue);
				
				switch( byValue ){
					case LDM:
						config.setLdm(true);
						break;
					case REPOSITORY:
						config.setRepository(true);
						break;
					case SERVICE:
						config.setService(true);
						break;
					case LIST:
						config.setListBean(true);
						break;
					case FORM:
						config.setFormBean(true);
					
				}
			}
		}

		
		return config;
		
	}
	
	public String extractClassName(String value){
		
		boolean possuiChave = value.indexOf("{") != -1;
		
		if(possuiChave){
			return value.substring(0, value.indexOf("{"));
		}
		
		return value;
	}
	
	public String[] getCreateConfiguration(String value){
		if(value.indexOf("{") == -1){
			return null;
		}
		
		value = value.substring(value.indexOf("{") +1, value.indexOf("}") );
		
		if(value == null){
			return null;
		}
		
		
		String[] values = value.split("\\.");
		return values;
	}
	
	public String getProperty(String key){
		try{
			return prop.getProperty(key);
		}catch (Exception e) {
			return null;
		}
	}
}