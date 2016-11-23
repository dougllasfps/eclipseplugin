package org.dougllas.classgenerator.classdefinition;

import org.dougllas.classgenerator.ClassInfo;
import org.dougllas.classgenerator.generator.RepositoryClassGenerator;

public class SDFilteredLazyDataModelClassDefinition implements ClassDefinition {

	@Override
	public String[] getAnnotations() {
		return null;
	}

	@Override
	public String getInheritanceStatement(ClassInfo info) {
		return String.format( " extends SDFilteredLazyDataModel<%s, %s> ", info.getClassName(), info.getClassName().concat(RepositoryClassGenerator.REPOSITORY_SUFIX) );
	}

	@Override
	public String[] getImports(ClassInfo info) {
		return new String[]{
				"br.gov.ce.seduc.crud.datamodel.SDFilteredLazyDataModel"
		};
	}

}
