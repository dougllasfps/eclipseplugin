package org.dougllas.classgenerator;

public class ConfiguracaoPacotes {

	private String artifactPackageDefinition;
	private String model;
	private String repository;
	private String service;
	private String ldm;
	private String beans;
	
	public String getArtifactPackageDefinition() {
		return artifactPackageDefinition;
	}
	public void setArtifactPackageDefinition(String artifactPackageDefinition) {
		this.artifactPackageDefinition = artifactPackageDefinition;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getRepository() {
		return repository;
	}
	public void setRepository(String repository) {
		this.repository = repository;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getLdm() {
		return ldm;
	}
	public void setLdm(String ldm) {
		this.ldm = ldm;
	}
	public String getBeans() {
		return beans;
	}
	public void setBeans(String beans) {
		this.beans = beans;
	}
}