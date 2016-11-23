package org.dougllas.classgenerator;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

import org.dougllas.classgenerator.classdefinition.GenericRepositoryClassDefinition;
import org.dougllas.classgenerator.classdefinition.SDCrudServiceClassDefinition;
import org.dougllas.classgenerator.classdefinition.SDFilteredLazyDataModelClassDefinition;
import org.dougllas.classgenerator.classdefinition.SDGenericListBeanClassDefinition;
import org.dougllas.classgenerator.classdefinition.SerializableModelClassDefinition;
import org.dougllas.classgenerator.generator.GeradorClasse;
import org.dougllas.classgenerator.generator.LDMClassGenerator;
import org.dougllas.classgenerator.generator.ListBeanClassGenerator;
import org.dougllas.classgenerator.generator.ModelClassGenerator;
import org.dougllas.classgenerator.generator.RepositoryClassGenerator;
import org.dougllas.classgenerator.generator.ServiceClassGenerator;
import org.dougllas.classgenerator.properties.ClasseConfig;
import org.dougllas.classgenerator.properties.PropertiesConfiguration;
import org.dougllas.classgenerator.properties.PropertiesConfigurationHandler;

public class MAIN {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		boolean propertyConfig = true;
		PropertiesConfiguration propertiesConfiguration = null;
		String[] classNames = null;
		String path = null;
		ClassFileGenerator classFileGenerator = null;
		
		try {
			PropertiesConfigurationHandler configurationHandler = new PropertiesConfigurationHandler("/generator.properties");
			propertiesConfiguration = configurationHandler.getConfiguration();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			propertyConfig = false;
		}
		
		try(Scanner scanner = new Scanner(System.in)){
			
			ConfiguracaoPacotes configuracao = new ConfiguracaoPacotes();

			if(!propertyConfig){
				
				System.out.print("Path do Projeto(deixar em branco vai gerar no disco local 'C'):");
				path = scanner.nextLine();

				System.out.print("Group Id:");
				String groupId = scanner.nextLine();
				
				System.out.print("Artifact Id:");
				String artifactId = scanner.nextLine();
				
				configuracao.setArtifactPackageDefinition(groupId.concat(".").concat(artifactId));
				
				System.out.print("Model Package:");
				String pacote = scanner.nextLine();
				configuracao.setModel(pacote);
				
				System.out.print("Repository Package:");
				pacote = scanner.nextLine();
				configuracao.setRepository(pacote);
				
				System.out.print("Service Package:");
				pacote = scanner.nextLine();
				configuracao.setService(pacote);
				
				System.out.print("LDM Package:");
				pacote = scanner.nextLine();
				configuracao.setLdm(pacote);
				
				System.out.print("List e Form Beans Package:");
				pacote = scanner.nextLine();
				configuracao.setBeans(pacote);;
				
				System.out.print("Classes (Separadas por Virgula):");
				classNames = scanner.nextLine().split(",");
				
				classFileGenerator = new ClassFileGenerator(configuracao, path);
				
				for (String className : classNames) {
					
					ClassInfo info = new  ClassInfo(className);
					
					gerarModel(configuracao, info, classFileGenerator);
					gerarRepository(configuracao, info, classFileGenerator);
					gerarService(configuracao, info, classFileGenerator);
					gerarLDM(configuracao, info, classFileGenerator);
					gerarListBean(configuracao, info, classFileGenerator);
				}
				
			}else{
				configuracao.setArtifactPackageDefinition(propertiesConfiguration.getGroupid().concat(".").concat(propertiesConfiguration.getArtifactid()));
				configuracao.setLdm(propertiesConfiguration.getLdm());
				configuracao.setModel(propertiesConfiguration.getModel());
				configuracao.setRepository(propertiesConfiguration.getRepository());
				configuracao.setService(propertiesConfiguration.getService());
				configuracao.setBeans(propertiesConfiguration.getBeans());
				
				path = propertiesConfiguration.getProjectLocation();
				
				Collection<ClasseConfig> classConfigurations = propertiesConfiguration.getClassConfigurations();

				classFileGenerator = new ClassFileGenerator(configuracao, path);
				
				for (ClasseConfig classeConfig : classConfigurations) {
					
					String className = classeConfig.getClassName();
					ClassInfo info = new  ClassInfo(className);
					
					gerarModel(configuracao, info, classFileGenerator);
					
					if(classeConfig.isNone()){
						return;
					}
					
					if(classeConfig.isAll() || classeConfig.isRepository()){
						gerarRepository(configuracao, info, classFileGenerator);
					}
					
					if(classeConfig.isAll() || classeConfig.isService()){
						gerarService(configuracao, info, classFileGenerator);
					}
					
					if(classeConfig.isAll() || classeConfig.isLdm()){
						gerarLDM(configuracao, info, classFileGenerator);
					}
					
					if(classeConfig.isAll() || classeConfig.isListBean()){
						gerarListBean(configuracao, info, classFileGenerator);
					}
					
				}
			}


			System.out.println("Processo concluído!");
			
		}
	}
	
	public static void gerarModel(ConfiguracaoPacotes configuracao, ClassInfo info, ClassFileGenerator classFileGenerator){
		GeradorClasse modelGenerator = new ModelClassGenerator(configuracao, new SerializableModelClassDefinition(), info);
		
		String modelClassText = modelGenerator.generate();
		classFileGenerator.criarClasse(modelGenerator.getClassName(), configuracao.getModel(), modelClassText);
	}
	
	public static void gerarRepository(ConfiguracaoPacotes configuracao, ClassInfo info, ClassFileGenerator classFileGenerator){
		GeradorClasse repositoryGenerator = new RepositoryClassGenerator(configuracao, new GenericRepositoryClassDefinition()	, info);
		String repClassText = repositoryGenerator.generate();
		classFileGenerator.criarClasse(repositoryGenerator.getClassName(), configuracao.getRepository(), repClassText);
	}
	
	public static void gerarService(ConfiguracaoPacotes configuracao, ClassInfo info, ClassFileGenerator classFileGenerator){
		GeradorClasse serviceGenerator = new ServiceClassGenerator(configuracao, new SDCrudServiceClassDefinition()	, info);
		
		String servClassText = serviceGenerator.generate();
		classFileGenerator.criarClasse(serviceGenerator.getClassName(), configuracao.getService(), servClassText);
	}
	
	public static void gerarLDM(ConfiguracaoPacotes configuracao, ClassInfo info, ClassFileGenerator classFileGenerator){
		GeradorClasse ldmGenerator = new LDMClassGenerator(configuracao, new SDFilteredLazyDataModelClassDefinition(), info);
		
		String ldmClassText = ldmGenerator.generate();
		classFileGenerator.criarClasse(ldmGenerator.getClassName(), configuracao.getLdm(), ldmClassText);
	}
	
	public static void gerarListBean(ConfiguracaoPacotes configuracao, ClassInfo info, ClassFileGenerator classFileGenerator){
		GeradorClasse listBeanGenerator = new ListBeanClassGenerator(configuracao, new SDGenericListBeanClassDefinition(), info);
		
		String ldmClassText = listBeanGenerator.generate();
		classFileGenerator.criarClasse(listBeanGenerator.getClassName(), configuracao.getBeans().concat(".").concat(info.getClassName().toLowerCase()), ldmClassText);
	}
}
