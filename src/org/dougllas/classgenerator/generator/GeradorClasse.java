package org.dougllas.classgenerator.generator;

import org.dougllas.classgenerator.GeradorCodigoFonte;

public interface GeradorClasse extends GeradorCodigoFonte {
	
	String getClassName();

	String generateHeader();
	
	String generateClassBody();
	
	String generate();
}
