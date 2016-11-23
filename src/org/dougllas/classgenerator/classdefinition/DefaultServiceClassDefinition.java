package org.dougllas.classgenerator.classdefinition;

import org.dougllas.classgenerator.ClassInfo;

public class DefaultServiceClassDefinition implements ClassDefinition{

	@Override
	public String[] getAnnotations() {
		return new String[]{"@Service"};
	}

	@Override
	public String getInheritanceStatement(ClassInfo info) {
		return String.format("extends", info.getClassName());
	}

	@Override
	public String[] getImports(ClassInfo info) {
		return null;
	}

}
