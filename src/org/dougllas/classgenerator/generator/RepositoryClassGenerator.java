package org.dougllas.classgenerator.generator;

import org.dougllas.classgenerator.ClassInfo;
import org.dougllas.classgenerator.CommonStatements;
import org.dougllas.classgenerator.ConfiguracaoPacotes;
import org.dougllas.classgenerator.classdefinition.ClassDefinition;

public class RepositoryClassGenerator extends DefaultClassGenerator implements GeradorClasse {

	public static final String REPOSITORY_SUFIX = "Repository";

	public RepositoryClassGenerator(ConfiguracaoPacotes configuracaoPacotes, ClassDefinition classDefinition, ClassInfo classInfo) {
		super(configuracaoPacotes, classDefinition, classInfo);
		classInfo.setRepositoryFullQualifyName( gerarRepositoryPakageDefinition() );
	}

	private String gerarRepositoryPakageDefinition() {
		return getConfiguracaoPacotes().getArtifactPackageDefinition()
				.concat(".")
				.concat(getConfiguracaoPacotes().getRepository());
	}

	@Override
	public String generateHeader() {
		String packageRepositoryClass = gerarRepositoryPakageDefinition();
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("package ");
		builder.append(packageRepositoryClass);
		builder.append(";\n\n");
		insertModelImport(getClassInfo(), builder);
		return builder.append(super.generateHeader()).toString();
	}

	@Override
	public String generateClassBody() {
		StringBuilder builder = new StringBuilder();
		
		if(getClassDefinition().getAnnotations() != null){
			for (String ann : getClassDefinition().getAnnotations()){
				builder.append(ann);
				builder.append("\n");
			}
		}
		
		String classDeclaration = String.format( CommonStatements.INTERFACE_DECLARATION, getClassInfo().getClassName() + REPOSITORY_SUFIX );
		String inheritanceStatement = getClassDefinition().getInheritanceStatement(getClassInfo());
		builder.append(classDeclaration);
		builder.append(inheritanceStatement);

		insertBrackets(builder);
		return builder.toString();
	}

	@Override
	public String getClassName() {
		return getClassInfo().getClassName().concat(REPOSITORY_SUFIX);
	}
}