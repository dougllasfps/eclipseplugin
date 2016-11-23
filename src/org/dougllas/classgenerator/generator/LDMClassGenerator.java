package org.dougllas.classgenerator.generator;

import org.dougllas.classgenerator.ClassInfo;
import org.dougllas.classgenerator.ConfiguracaoPacotes;
import org.dougllas.classgenerator.classdefinition.ClassDefinition;

public class LDMClassGenerator extends DefaultClassGenerator implements GeradorClasse {
	
	public static final String LDM_SUFIX = "LDM";

	public LDMClassGenerator(ConfiguracaoPacotes configuracaoPacotes, ClassDefinition classDefinition,	ClassInfo classInfo) {
		super(configuracaoPacotes, classDefinition, classInfo);
		classInfo.setLdmFullQualifyName(generatePakageDefinition());
	}
	
	private String generatePakageDefinition() {
		return getConfiguracaoPacotes().getArtifactPackageDefinition()
				.concat(".")
				.concat(getConfiguracaoPacotes().getLdm());
	}
	
	@Override
	public String generateHeader() {
		StringBuilder builder = new StringBuilder();
		builder.append("package ");
		builder.append(generatePakageDefinition());
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
		
		if(getClassInfo().getRepositoryFullQualifyName() != null){
			builder.append("import ");
			builder.append(getClassInfo().getRepositoryFullQualifyName());
			builder.append(String.format(".%s;\n",getClassInfo().getClassName().concat(RepositoryClassGenerator.REPOSITORY_SUFIX)));
		}
		
		insertModelImport(getClassInfo(), builder);
		
		return builder.toString();
	}

	@Override
	public String getClassName() {
		return getClassInfo().getClassName().concat(LDM_SUFIX);
	}
}