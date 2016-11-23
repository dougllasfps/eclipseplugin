package org.dougllas.classgenerator.classdefinition;

import org.dougllas.classgenerator.ClassInfo;

public class JpaAndSpecificationRepositoryClassDefinition implements ClassDefinition{
	
	@Override
	public String getInheritanceStatement( ClassInfo info ) {
		return String.format("extends JpaRepository<%s, %s>, JpaSpecificationExecutor<%s>", info.getClassName(), "Integer",info.getClassName() );
	}

	@Override
	public String[] getImports(ClassInfo info) {
		return new String[]{
				"org.springframework.data.jpa.repository.JpaRepository",
				"org.springframework.stereotype.Repository",
				"org.springframework.data.jpa.repository.JpaSpecificationExecutor"
		};
	}
	
	@Override
	public String[] getAnnotations() {
		return new String[]{"@Repository"};
	}
}