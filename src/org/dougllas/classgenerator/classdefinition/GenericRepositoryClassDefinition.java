package org.dougllas.classgenerator.classdefinition;

import org.dougllas.classgenerator.ClassInfo;

public class GenericRepositoryClassDefinition implements ClassDefinition {
	
	@Override
	public String getInheritanceStatement(final ClassInfo info) {
		return String.format("extends GenericRepository<%s, %s>", info.getClassName(), "Integer");
	}

	@Override
	public String[] getImports(ClassInfo info) {
		return new String[]{
				"br.gov.ce.seduc.generics.repository.GenericRepository",
				"org.springframework.stereotype.Repository"
		};
	}
	
	@Override
	public String[] getAnnotations() {
		return new String[]{"@Repository"};
	}
	
}