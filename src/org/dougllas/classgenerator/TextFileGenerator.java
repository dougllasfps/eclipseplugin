package org.dougllas.classgenerator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileGenerator {

	public static void gerarArquivo( String filePath, String text , boolean override) {
        try {  
        	File file = new File(filePath);
        	
        	if(!override && file.exists()){
        		return;
        	}
        	
        	File dir = new File(file.getParent());

    		if (!dir.exists()) {
    			dir.mkdirs();
    		}
    		
			FileWriter arquivo = new FileWriter(file);  
            arquivo.write(text);  
            arquivo.flush();
            arquivo.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
	}
	
	public synchronized void save(String path, byte[] data) throws IOException {

		BufferedOutputStream outputStream = null;
		File arquivo = new File(path);
		File dir = new File(arquivo.getParent());

		if (!dir.exists()) {
			dir.mkdirs();
		}

		try {
			outputStream = new BufferedOutputStream(new FileOutputStream(arquivo));
			outputStream.write(data);
		} finally {
			outputStream.flush();
			outputStream.close();
		}
	}
}