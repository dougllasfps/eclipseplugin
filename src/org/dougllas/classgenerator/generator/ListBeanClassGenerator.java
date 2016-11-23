package org.dougllas.classgenerator.generator;

import org.dougllas.classgenerator.ClassInfo;
import org.dougllas.classgenerator.ConfiguracaoPacotes;
import org.dougllas.classgenerator.classdefinition.ClassDefinition;

public class ListBeanClassGenerator extends DefaultClassGenerator implements GeradorClasse {
	
	public ListBeanClassGenerator(ConfiguracaoPacotes configuracaoPacotes, ClassDefinition classDefinition,
			ClassInfo classInfo) {
		super(configuracaoPacotes, classDefinition, classInfo);
		classInfo.setBeansFullQualifyName(generatePakageDefinition());
	}
	
	@Override
	public String generateHeader() {
		StringBuilder builder = new StringBuilder();
		builder.append("package ");
		builder.append(generatePakageDefinition().concat(".").concat(getClassInfo().getClassName().toLowerCase()));
		builder.append(";\n\n");
		
		if(getClassDefinition() != null){
			String[] defaultImports = getClassDefinition().getImports(getClassInfo());
			
			if(defaultImports != null){
				for (String imp : defaultImports) {
					builder.append("import ");
					builder.append(imp);
					builder.append(";\n");
				}
			}
		}
		
		if(getClassInfo().getServiceFullQualifyName() != null){
			builder.append("import ");
			builder.append(getClassInfo().getServiceFullQualifyName());
			builder.append(String.format(".%s;\n",getClassInfo().getClassName().concat(ServiceClassGenerator.SERVICE_SUFIX)));
		}
		
		if(getClassInfo().getLdmFullQualifyName() != null){
			builder.append("import ");
			builder.append(getClassInfo().getLdmFullQualifyName());
			builder.append(String.format(".%s;\n",getClassInfo().getClassName().concat(LDMClassGenerator.LDM_SUFIX)));
		}
		
		insertModelImport(getClassInfo(), builder);
		
		return builder.toString();
	}
	
	private static final String SUFIX = "ListBean";
	
	@Override
	public String getClassName() {
		return getClassInfo().getClassName().concat(SUFIX);
	}
	
	private String generatePakageDefinition() {
		return getConfiguracaoPacotes().getArtifactPackageDefinition()
				.concat(".")
				.concat(getConfiguracaoPacotes().getBeans());
	}
}