package org.dougllas.classgenerator;

public class ClassFileGenerator {
	
	private ConfiguracaoPacotes configuracaoPacotes;
	private String projectPath;
	
	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public ClassFileGenerator(ConfiguracaoPacotes configuracaoPacotes){
		this.configuracaoPacotes = configuracaoPacotes;
	}
	
	public ClassFileGenerator(ConfiguracaoPacotes configuracaoPacotes, String projectPath) {
		super();
		this.configuracaoPacotes = configuracaoPacotes;
		this.projectPath = projectPath;
	}

	private String getClassFilePath(String classFileName, String classPackage){
		String pathDoProjeto = projectPath == null ? System.getProperty("user.dir") : projectPath  + "\\src\\main\\java";
		String artifactPackageDefinition = configuracaoPacotes.getArtifactPackageDefinition();
		artifactPackageDefinition = artifactPackageDefinition.replace(".", "\\");
		classPackage = classPackage.replace(".", "\\");
		String path = pathDoProjeto.concat("\\").concat(artifactPackageDefinition).concat("\\" + classPackage + "\\").concat(classFileName + ".java");
		System.out.println(path);
		return path;
	}
	
	public void criarClasse(String className, String classPackage, String classText){
		String path = getClassFilePath(className, classPackage);
		TextFileGenerator.gerarArquivo(path, classText, false);
	}
	
	public void criarClasse(String className, String classPackage, String classText, boolean override){
		String path = getClassFilePath(className, classPackage);
		TextFileGenerator.gerarArquivo(path, classText, override);
	}
}