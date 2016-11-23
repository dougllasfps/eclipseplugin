package org.dougllas.classgenerator.classdefinition;

import org.dougllas.classgenerator.ClassInfo;

public interface ClassDefinition {
	
	String[] getAnnotations();
	
	String getInheritanceStatement(ClassInfo info);
	
	String[] getImports(ClassInfo info);
	
}