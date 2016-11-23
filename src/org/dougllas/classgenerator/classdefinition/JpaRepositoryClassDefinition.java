package org.dougllas.classgenerator.classdefinition;

import org.dougllas.classgenerator.ClassInfo;

public class JpaRepositoryClassDefinition implements ClassDefinition{
	
	@Override
	public String getInheritanceStatement( ClassInfo info ) {
		return String.format("extends JpaRepository<%s, %s>", info.getClassName(), "Integer");
	}

	@Override
	public String[] getImports(ClassInfo info) {
		return new String[]{
				"org.springframework.data.jpa.repository.JpaRepository",
				"org.springframework.stereotype.Repository"
		};
	}
	
	@Override
	public String[] getAnnotations() {
		return new String[]{"@Repository"};
	}
}