package org.dougllas.classgenerator;

public class ClassInfo {

	private String pacote;
	private String className;
	private String serviceFullQualifyName;
	private String repositoryFullQualifyName;
	private String modelFullQualifyName;
	private String ldmFullQualifyName;
	private String beansFullQualifyName;
	
	public ClassInfo(String className) {
		super();
		this.className = className;
	}

	public String getPacote() {
		return pacote;
	}

	public void setPacote(String pacote) {
		this.pacote = pacote;
	}
	
	public String getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}

	public String getServiceFullQualifyName() {
		return serviceFullQualifyName;
	}

	public void setServiceFullQualifyName(String serviceFullQualifyName) {
		this.serviceFullQualifyName = serviceFullQualifyName;
	}

	public String getRepositoryFullQualifyName() {
		return repositoryFullQualifyName;
	}

	public void setRepositoryFullQualifyName(String repositoryFullQualifyName) {
		this.repositoryFullQualifyName = repositoryFullQualifyName;
	}

	public String getModelFullQualifyName() {
		return modelFullQualifyName;
	}

	public void setModelFullQualifyName(String modelFullQualifyName) {
		this.modelFullQualifyName = modelFullQualifyName;
	}

	public String getLdmFullQualifyName() {
		return ldmFullQualifyName;
	}

	public void setLdmFullQualifyName(String ldmFullQualifyName) {
		this.ldmFullQualifyName = ldmFullQualifyName;
	}

	public String getBeansFullQualifyName() {
		return beansFullQualifyName;
	}

	public void setBeansFullQualifyName(String beansFullQualifyName) {
		this.beansFullQualifyName = beansFullQualifyName;
	}
}