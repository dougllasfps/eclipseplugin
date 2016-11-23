package org.dougllas.classgenerator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import javafx.application.Application;
import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application{
	
	private static Display display;
	private static Shell shell;
	
	public static void main(String[] args) {
		bySWT();
	}
	
	public static void byFX(String[] args){
		launch(args);
	}

	private static void bySWT() {
		
		Display.getDefault().asyncExec(() -> {
			if(shell == null){
				StageController controller = new StageController(null);
				display =  PlatformUI.createDisplay();		
				shell = new Shell(display);
				shell.setLayout(new FillLayout());
				FXCanvas canvas = new FXCanvas(shell, SWT.NONE);
				
				Scene scene = controller.loadInPrimaryStage(ApplicationType.CONFIG_SCREEN);
				canvas.setScene(scene);
				
				shell.setSize((int)scene.getWidth(), (int) scene.getHeight());
			}

			shell.open();

			while(!shell.isDisposed()){
				if(!display.readAndDispatch()){
					display.sleep();
				}
			}
			
		});
		
//		display.dispose();
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		StageController controller = new StageController(primaryStage);
		controller.loadInPrimaryStage(ApplicationType.CONFIG_SCREEN);
	}
}