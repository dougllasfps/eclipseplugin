package org.dougllas.classgenerator;

import java.util.Collection;

import org.dougllas.classgenerator.properties.ClasseConfig;

public class DefaultConfiguration implements Configuration {

	private String projectLocation;
	private String groupid;
	private String artifactid;
	private String model;
	private String service;
	private String repository;
	private String ldm;
	private String createMode;
	private String beans;
	private String[] classes;
	private Collection<ClasseConfig> classConfigurations;
	
	public String getProjectLocation() {
		return projectLocation;
	}
	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getArtifactid() {
		return artifactid;
	}
	public void setArtifactid(String artifactid) {
		this.artifactid = artifactid;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getRepository() {
		return repository;
	}
	public void setRepository(String repository) {
		this.repository = repository;
	}
	public String getLdm() {
		return ldm;
	}
	public void setLdm(String ldm) {
		this.ldm = ldm;
	}
	public String getCreateMode() {
		return createMode;
	}
	public void setCreateMode(String createMode) {
		this.createMode = createMode;
	}
	public String getBeans() {
		return beans;
	}
	public void setBeans(String beans) {
		this.beans = beans;
	}
	public String[] getClasses() {
		return classes;
	}
	public void setClasses(String[] classes) {
		this.classes = classes;
	}
	public Collection<ClasseConfig> getClassConfigurations() {
		return classConfigurations;
	}
	public void setClassConfigurations(Collection<ClasseConfig> classConfigurations) {
		this.classConfigurations = classConfigurations;
	}
}