package org.dougllas.classgenerator.classdefinition;

import org.dougllas.classgenerator.ClassInfo;
import org.dougllas.classgenerator.generator.LDMClassGenerator;
import org.dougllas.classgenerator.generator.ServiceClassGenerator;

public class SDGenericListBeanClassDefinition implements ClassDefinition {

	@Override
	public String[] getAnnotations() {
		return new String[]{
				"@ManagedBean",
				"@ViewScoped"
		};
	}

	@Override
	public String getInheritanceStatement(ClassInfo info) {
		return String.format("extends SDGenericListBean<%s, %s, %s>", info.getClassName(), info.getClassName() + ServiceClassGenerator.SERVICE_SUFIX, info.getClassName() + LDMClassGenerator.LDM_SUFIX);
	}

	@Override
	public String[] getImports(ClassInfo info) {
		return new String[]{
				"javax.faces.bean.ManagedBean",
				"javax.faces.bean.ViewScoped",
				"br.gov.ce.seduc.crud.view.SDGenericListBean"
		};
	}
}