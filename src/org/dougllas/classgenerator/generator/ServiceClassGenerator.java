package org.dougllas.classgenerator.generator;

import org.dougllas.classgenerator.ClassInfo;
import org.dougllas.classgenerator.CommonStatements;
import org.dougllas.classgenerator.ConfiguracaoPacotes;
import org.dougllas.classgenerator.GeradorCodigoFonte;
import org.dougllas.classgenerator.classdefinition.ClassDefinition;

public class ServiceClassGenerator extends DefaultClassGenerator implements GeradorCodigoFonte {

	public static final String SERVICE_SUFIX = "Service";
	
	public ServiceClassGenerator(ConfiguracaoPacotes configuracaoPacotes, ClassDefinition classDefinition, ClassInfo classInfo) {
		super(configuracaoPacotes, classDefinition, classInfo);
		classInfo.setServiceFullQualifyName(generatePakageDefinition());
	}
	
	private String generatePakageDefinition() {
		return getConfiguracaoPacotes().getArtifactPackageDefinition()
				.concat(".")
				.concat(getConfiguracaoPacotes().getService());
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
			builder.append(".*;\n");
		}
		
		insertModelImport(getClassInfo(), builder);
		
		return builder.toString();
	}
	
	@Override
	public String generateClassBody() {
		ClassInfo info = getClassInfo();
		StringBuilder sb = new StringBuilder();
		
		if(getClassDefinition().getAnnotations() != null){
			for (String ann : getClassDefinition().getAnnotations()){
				sb.append(ann);
				sb.append("\n");
			}
		}
		
		String classDefinition = String.format(CommonStatements.CLASS_DECLARATION, info.getClassName() + SERVICE_SUFIX);
		sb.append(classDefinition);
		
		if(getClassDefinition() != null && getClassDefinition().getInheritanceStatement(info) != null){
			sb.append(getClassDefinition().getInheritanceStatement(info));
		}
		
		return insertBrackets(sb).toString();
	}

	@Override
	public String getClassName() {
		return getClassInfo().getClassName().concat(SERVICE_SUFIX);
	}
}