package org.dougllas.classgenerator.handlers;

import org.dougllas.classgenerator.Launcher;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;

public class SeducClassCreatorHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		event.getApplicationContext();
		
		Display.getDefault().asyncExec( ()->
			Launcher.main(null)
		);
		
		return null;
	}
}