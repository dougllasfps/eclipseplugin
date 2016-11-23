package org.dougllas.classgenerator.classdefinition;

import org.dougllas.classgenerator.ClassInfo;

public class CrudAndSpecificationRepositoryClassDefinition implements ClassDefinition {

	@Override
	public String getInheritanceStatement(ClassInfo info) {
		return String.format("extends CrudRepository<%s, %s>, JpaSpecificationExecutor<%s>", info.getClassName(), "Integer", info.getClassName() );
	}

	@Override
	public String[] getImports(ClassInfo info) {
		return new String[]{
				"org.springframework.stereotype.Repository",
				"org.springframework.data.jpa.repository.JpaSpecificationExecutor",
				"org.springframework.data.repository.CrudRepository"
		};
	}

	@Override
	public String[] getAnnotations() {
		return new String[]{"@Repository"};
	}

}
