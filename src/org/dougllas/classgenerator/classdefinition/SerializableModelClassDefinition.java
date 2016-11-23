package org.dougllas.classgenerator.classdefinition;

import org.dougllas.classgenerator.ClassInfo;

public class SerializableModelClassDefinition implements ClassDefinition {

	@Override
	public String getInheritanceStatement(ClassInfo info) {
		return "implements Serializable";
	}

	@Override
	public String[] getImports(ClassInfo info) {
		return new String[]{"java.io.Serializable","javax.persistence.Entity"};
	}

	@Override
	public String[] getAnnotations() {
		return  new String[]{"@Entity"};
	}

}
