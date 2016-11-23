package org.dougllas.classgenerator;

import java.util.List;

public enum ApplicationType {
	CONFIG_SCREEN("ConfigScreen.fxml", "", null)
	;
	private final String resource;
	private final String title;
	private final ApplicationType owner;

	private ApplicationType(String resource, String title, ApplicationType owner) {
		this.resource = resource;
		this.title = title;
		this.owner = owner;
	}

	public String getResource() {
		return resource;
	}

	public String getTitle() {
		return title;
	}

	public boolean in(List<ApplicationType> list) {
		for (ApplicationType type : list) {
			if (type.equals(this)) {
				return true;
			}
		}
		return false;
	}

	public ApplicationType getOwner() {
		return owner;
	}
}