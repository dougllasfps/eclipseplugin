package org.dougllas.classgenerator;

@FunctionalInterface
public interface GeradorCodigoFonte {

	String generate( ClassInfo classInfo  );

}