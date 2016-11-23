/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dougllas.classgenerator;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author dougllas.sousa
 */
public class StageController {

	public static final String FXML_PACKAGE_LOCATION = "/";
	
    private final Map<ApplicationType, Parent> screens;
    private final Map<ApplicationType, Stage> stages;
    private final Map<ApplicationType, Controller> controllers;
    
    private final Stage primaryStage;

    public StageController( Stage primaryStage ) {
        this.screens = new HashMap<ApplicationType, Parent>();
        this.stages = new HashMap<ApplicationType, Stage>();
        this.controllers = new HashMap<ApplicationType, Controller>();
        this.primaryStage = primaryStage;
    }
    
    @SuppressWarnings("unchecked")
	public <T> T getController(ApplicationType applicationType){
        return (T) controllers.get(applicationType);
    }
    
    public Stage getStage(ApplicationType applicationType){
    	 return stages.get(applicationType);
    }
    
    private void registerScreen(ApplicationType applicationType, Parent parent) {
        screens.put(applicationType, parent);
    }
    
    private void registerController(ApplicationType applicationType, Controller controller) {
        controllers.put(applicationType, controller);
    }
    
    private void registerStage(ApplicationType applicationType, Stage stage) {
        stages.put(applicationType, stage);
    }
    
    private void unregisterScreen(ApplicationType applicationType) {
        screens.remove(applicationType);
    }
    
    private void unregisterController(ApplicationType applicationType) {
        controllers.remove(applicationType);
    }
    
    private void unregisterStage(ApplicationType applicationType) {
        stages.remove(applicationType);
    }

    public Parent loadScreen(final ApplicationType applicationType) {
        
        String resource =FXML_PACKAGE_LOCATION + applicationType.getResource();
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource(resource) /*, ResourceBundleService.getInstance()*/ );
            Parent loadScreen = (Parent) fxmlLoader.load();
            
            Controller controller  = ((Controller) fxmlLoader.getController());
            controller.setStageController(this);
            
            registerScreen(applicationType, loadScreen);
            registerController(applicationType, controller);
            
            return loadScreen;
        } catch (LoadException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            return null;
		}
    }
    
    public void closeScreen(final ApplicationType applicationType){
        Stage stage = stages.get(applicationType);
        unregisterScreen(applicationType);
        unregisterStage(applicationType);
        unregisterController(applicationType);
        
        if(stage != null ){
            stage.close();
        }
    }
    
    public Scene loadInPrimaryStage(ApplicationType type){
    	 Parent mainScreen = loadScreen(type);
         Scene scene = new Scene(mainScreen);
         
//         primaryStage.setScene(scene);
//         primaryStage.setTitle(type.getTitle());
//         primaryStage.centerOnScreen();
//         primaryStage.setResizable(false);
//         primaryStage.show();
         
         Controller controller = getController(type);
         
         if(controller != null){
        	 controller.setStage(primaryStage);
         }
         
         applyScreenConfigurations(type, scene, primaryStage);
         
         return scene;
    }
    
    public void openScreenInANewWindow(final ApplicationType applicationType){
       openScreenInANewWindow(applicationType, null);
    }
    
    public void openScreenInANewWindow(final ApplicationType applicationType, boolean fullScreen){
        openScreenInANewWindow(applicationType, null, fullScreen);
     }
    
    public void openScreenInANewWindow(final ApplicationType applicationType, Map<String, Object> params, boolean fullScreen){
        openScreenInANewWindow(applicationType, params, null, fullScreen);
    }
    
    public void openScreenInANewWindow(final ApplicationType applicationType, Map<String, Object> params){
        openScreenInANewWindow(applicationType, params, null, false);
    }
    
    public void openScreenInANewWindow(final ApplicationType applicationType, Map<String, Object> params, Stage stage){
        openScreenInANewWindow(applicationType, params, stage, false);
    }
    
    public void openScreenInANewWindow(final ApplicationType applicationType, Map<String, Object> params, Stage owner, boolean fullScreen){
        
        if(isOpen(applicationType)){
            return;
        }
        
        Parent parent = screens.get(applicationType);
        if(parent == null){
            parent = loadScreen(applicationType);
        }
        
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(applicationType.getTitle());
        
        stage.centerOnScreen();
        stage.setResizable(false);
        
        Controller controller = getController(applicationType);
        
        if(controller != null){
        	initControllerProperties( controller, params, stage, scene);
        }
        
        applyScreenConfigurations(applicationType, scene, stage, owner);
        registerStage(applicationType, stage);
        
    	stage.setFullScreen(fullScreen);
        stage.show();
    }

	private void initControllerProperties(Controller controller, Map<String, Object> params, Stage stage, Scene scene) {
		controller.setStage(stage);
		controller.setScene(scene);
		controller.setParameters(params);
	}

	private void applyScreenConfigurations(final ApplicationType applicationType, final Scene scene, final Stage stage) {
		applyScreenConfigurations(applicationType, scene, stage, null);
	}
	
	private void applyScreenConfigurations(final ApplicationType applicationType, final Scene scene, final Stage stage, final Stage owner) {
		registerStage(applicationType, stage);
		
		//para a tecla ESC fechar qualquer tela... obrigado.
        Platform.runLater(() -> {
//            ObservableMap<KeyCombination, Runnable> accelerators = scene.getAccelerators();
//            accelerators.put(new KeyCodeCombination(KeyCode.ESCAPE), (Runnable) () -> {
//                closeScreen(applicationType);
//            });
        	stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					stage.hide();
				}
			});
        });
        
	}
    
    public boolean isOpen(ApplicationType type){
        return stages.get(type) != null && stages.get(type).isShowing();
    }
}