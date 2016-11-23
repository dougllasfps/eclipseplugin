package org.dougllas.classgenerator.generator;

import org.dougllas.classgenerator.ClassInfo;
import org.dougllas.classgenerator.CommonStatements;
import org.dougllas.classgenerator.ConfiguracaoPacotes;
import org.dougllas.classgenerator.classdefinition.ClassDefinition;

public abstract class DefaultClassGenerator implements GeradorClasse {

	private ConfiguracaoPacotes configuracaoPacotes;
	private ClassDefinition inheritanceStatement;
	private ClassInfo classInfo;
	
	public DefaultClassGenerator(ConfiguracaoPacotes configuracaoPacotes, ClassDefinition classDefinition, ClassInfo classInfo) {
		this.configuracaoPacotes = configuracaoPacotes;
		this.inheritanceStatement = classDefinition;
		this.classInfo = classInfo;
	}
	
	public ClassInfo getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	public ClassDefinition getClassDefinition() {
		return inheritanceStatement;
	}
	
	public ConfiguracaoPacotes getConfiguracaoPacotes() {
		return configuracaoPacotes;
	}
	
	@Override
	public String generateClassBody() {
		StringBuilder sb = new StringBuilder();
		
		if(getClassDefinition().getAnnotations() != null){
			for (String ann : getClassDefinition().getAnnotations()){
				sb.append(ann);
				sb.append("\n");
			}
		}
		
		ClassInfo info = getClassInfo();
		String classDeclaration = String.format(CommonStatements.CLASS_DECLARATION, getClassName());
		sb.append(classDeclaration);
		
		if(getClassDefinition() != null && getClassDefinition().getInheritanceStatement(info) != null){
			sb.append(getClassDefinition().getInheritanceStatement(info));
		}
		
		return insertBrackets(sb).toString();
	}
	
	@Override
	public String generate() {
		return this.generate(getClassInfo());
	}
	
	@Override
	public String generate(ClassInfo classInfo) {
		String body = generateClassBody();
		return generateHeader().concat("\n").concat(body);
	}

	public String generateHeader() {
		StringBuilder builder = new StringBuilder();
		
		if(getClassDefinition() != null){
			String[] defaultImports = getClassDefinition().getImports(classInfo);
			
			if(defaultImports != null){
				for (String imp : defaultImports) {
					builder.append("import ");
					builder.append(imp);
					builder.append(";\n");
				}
			}
		}
		
		return builder.toString();
	}
	
	public StringBuilder insertBrackets(StringBuilder builder) {
		builder.append(CommonStatements.OPENING_BRACKET);
		builder.append("\n\n");
		builder.append(CommonStatements.CLOSING_BRACKET);
		return builder;
	}
	
	public StringBuilder insertModelImport(ClassInfo classInfo, StringBuilder builder){
		builder.append("import ");
		builder.append(getConfiguracaoPacotes().getArtifactPackageDefinition());
		builder.append(".");
		builder.append(getConfiguracaoPacotes().getModel());
		builder.append(".");
		builder.append(classInfo.getClassName());
		builder.append(";\n\n");
		return builder;
	}
}