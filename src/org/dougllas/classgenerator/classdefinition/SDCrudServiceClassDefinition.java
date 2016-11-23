package org.dougllas.classgenerator.classdefinition;

import org.dougllas.classgenerator.ClassInfo;
import org.dougllas.classgenerator.generator.RepositoryClassGenerator;

public class SDCrudServiceClassDefinition implements ClassDefinition{

	@Override
	public String[] getAnnotations() {
		return new String[]{"@Service"};
	}

	@Override
	public String getInheritanceStatement(ClassInfo info) {
		return String.format("extends SDCrudService<%s, Integer, %s>", info.getClassName(), info.getClassName() + RepositoryClassGenerator.REPOSITORY_SUFIX);
	}

	@Override
	public String[] getImports(ClassInfo info) {
		return new String[]{
				"br.gov.ce.seduc.generics.service.SDCrudService",
				"org.springframework.stereotype.Service"
		};
	}

}
