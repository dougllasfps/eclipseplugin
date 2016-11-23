package org.dougllas.classgenerator.generator;

import org.dougllas.classgenerator.ClassInfo;
import org.dougllas.classgenerator.CommonStatements;
import org.dougllas.classgenerator.ConfiguracaoPacotes;
import org.dougllas.classgenerator.classdefinition.ClassDefinition;

public class ModelClassGenerator extends DefaultClassGenerator implements GeradorClasse {

	public ModelClassGenerator(ConfiguracaoPacotes configuracaoPacotes, ClassDefinition inheritanceStatement,ClassInfo classInfo) {
		super(configuracaoPacotes, inheritanceStatement, classInfo);
		classInfo.setModelFullQualifyName(generatePakageDefinition());
	}
	
	private String generatePakageDefinition() {
		return getConfiguracaoPacotes().getArtifactPackageDefinition()
				.concat(".")
				.concat(getConfiguracaoPacotes().getModel());
	}

	@Override
	public String generateClassBody() {
		ClassInfo info = getClassInfo();
		String classDeclaration = String.format(CommonStatements.CLASS_DECLARATION, info.getClassName());
		StringBuilder sb = new StringBuilder(classDeclaration);
		
		if(getClassDefinition() != null && getClassDefinition().getInheritanceStatement(info) != null){
			sb.append(getClassDefinition().getInheritanceStatement(info));
		}
		
		return insertBrackets(sb).toString();
	}
	
	@Override
	public String generateHeader() {
		ClassInfo classInfo = getClassInfo();
		
		StringBuilder builder = new StringBuilder();
		builder.append("package ");
		builder.append(getConfiguracaoPacotes().getArtifactPackageDefinition());
		builder.append(".");
		builder.append(getConfiguracaoPacotes().getModel());
		builder.append(";\n\n");
		
		if(getClassDefinition() != null && getClassDefinition().getImports(classInfo) != null){
			for (String imp : getClassDefinition().getImports(classInfo)){
				builder.append("import ");
				builder.append(imp);
				builder.append(";\n");
			}
		}
		return builder.toString();
	}

	@Override
	public String getClassName() {
		return getClassInfo().getClassName();
	}

}