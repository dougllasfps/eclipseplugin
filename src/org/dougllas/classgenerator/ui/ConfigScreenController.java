package org.dougllas.classgenerator.ui;

import java.util.ArrayList;
import java.util.List;

import org.dougllas.classgenerator.ClassFileGenerator;
import org.dougllas.classgenerator.ClassInfo;
import org.dougllas.classgenerator.ConfiguracaoPacotes;
import org.dougllas.classgenerator.Controller;
import org.dougllas.classgenerator.DefaultConfiguration;
import org.dougllas.classgenerator.MAIN;
import org.dougllas.classgenerator.properties.ClasseConfig;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.widgets.Display;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ConfigScreenController extends Controller{

	private DefaultConfiguration configuration;
	
	@FXML
	private TextField 
				inputGroupId, 
				inputArtifactId, 
				inputModelPackage, 
				inputRepositoryPackage, 
				inputServicePackage, 
				inputLDMPack, 
				inputFormAndListPackage,
				inputClassName;
	
	@FXML
	private CheckBox checkRep, checkService, checkLDM, checkList, checkForm;
	
	@FXML
	private ComboBox<String> comboProjetos;
	
	private String workspace;
	
	@Override
	public void postInitialize() {
		this.setConfiguration(new DefaultConfiguration());
		IWorkspace workspaceConfig = ResourcesPlugin.getWorkspace();
		IProject[] projects = workspaceConfig.getRoot().getProjects();
		
		List<String> projetos = new ArrayList<>();
		
		for (IProject project : projects){
			projetos.add(project.getName());
		}
		
		comboProjetos.setItems(FXCollections.observableArrayList(projetos));
		workspace = workspaceConfig.getRoot().getLocation().toString();
//		Display.getDefault().asyncExec( ()->{
//			if(inputGroupId != null){
//				inputGroupId.setText(workspace);
//			}
//		});
	}

	public DefaultConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(DefaultConfiguration configuration) {
		this.configuration = configuration;
	}
	
	@FXML
	public void action(ActionEvent evt){
		String className = inputClassName.getText();

		if(className == null || className.trim().isEmpty()){
			Alert alert = generateAlert("Informe o nome da Classe de Modelo.", AlertType.ERROR);
			alert.show();
			return;
		}
		
		String groupId = inputGroupId.getText();
		String artifactId = inputArtifactId.getText();
		String modelPackage = inputModelPackage.getText() == null ? "model" : inputModelPackage.getText() ;
		String repoPackage = inputRepositoryPackage.getText() == null ? "repository" : inputRepositoryPackage.getText() ;
		String servicePack = inputServicePackage.getText() == null ? "service" : inputServicePackage.getText() ;
		String ldmPack = inputLDMPack.getText() == null ? "ldm" : inputLDMPack.getText() ;
		String formAndListPack = inputFormAndListPackage.getText() == null ? "view.bean" : inputFormAndListPackage.getText() ;
		
		String projeto = comboProjetos.getSelectionModel().getSelectedItem();
		String projectPath = workspace.concat("/").concat(projeto);
		
		configuration.setGroupid(groupId);
		configuration.setArtifactid(artifactId);
		configuration.setModel(modelPackage);
		configuration.setRepository(repoPackage);
		configuration.setService(servicePack);
		configuration.setLdm(ldmPack);

		ClassInfo info = new ClassInfo(className);
		
		ConfiguracaoPacotes config = new ConfiguracaoPacotes();

		if(groupId != null && artifactId != null){
			config.setArtifactPackageDefinition(groupId + "." +artifactId);
		}
		
		config.setLdm(ldmPack);
		config.setBeans(formAndListPack);
		config.setModel(modelPackage);
		config.setRepository(repoPackage);
		config.setService(servicePack);
		
		ClasseConfig classConfig = new ClasseConfig();
		classConfig.setClassName(className);
		
        ClassFileGenerator generator = new ClassFileGenerator(config, projectPath);
		
        MAIN.gerarModel(config, info, generator);
        
        if(checkLDM.isSelected())
        	MAIN.gerarLDM(config, info, generator);
        if(checkService.isSelected())
        	MAIN.gerarService(config, info, generator);
        if(checkRep.isSelected())
        	MAIN.gerarRepository(config, info, generator);
        if(checkList.isSelected())
        	MAIN.gerarListBean(config, info, generator);
        
        generateAlert("Gerado Com sucesso! Dê um refresh no projeto.", AlertType.INFORMATION).show();
	}
	
	public Alert generateAlert(String msg, AlertType alertType){
		Alert alert = new Alert(alertType);
		alert.setContentText(msg);
		return alert;
	}
}
