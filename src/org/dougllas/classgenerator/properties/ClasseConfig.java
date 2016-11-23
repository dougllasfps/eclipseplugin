package org.dougllas.classgenerator.properties;

public class ClasseConfig {

	private String className;
	private boolean repository;
	private boolean service;
	private boolean listBean;
	private boolean formBean;
	private boolean ldm;
	private boolean all;
	private boolean none;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public boolean isRepository() {
		return repository;
	}
	public void setRepository(boolean repository) {
		this.repository = repository;
	}
	public boolean isService() {
		return service;
	}
	public void setService(boolean service) {
		this.service = service;
	}
	public boolean isLdm() {
		return ldm;
	}
	public void setLdm(boolean ldm) {
		this.ldm = ldm;
	}
	public boolean isAll() {
		return all;
	}
	public void setAll(boolean all) {
		this.all = all;
	}
	public boolean isNone() {
		return none;
	}
	public void setNone(boolean none) {
		this.none = none;
	}
	public boolean isListBean() {
		return listBean;
	}
	public void setListBean(boolean listBean) {
		this.listBean = listBean;
	}
	public boolean isFormBean() {
		return formBean;
	}
	public void setFormBean(boolean formBean) {
		this.formBean = formBean;
	}
}
