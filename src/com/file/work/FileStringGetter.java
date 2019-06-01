package com.file.work;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileStringGetter {
	
	public void processFile(final String filePath, IFileStringsWriter stringWriter){
			
		final File file  = getFileFromFilePath(filePath);		
		try(BufferedReader br = 
				new BufferedReader(new InputStreamReader(new FileInputStream(file)))){
			
			final String str = stringWriter.proceedString(br);
			System.out.println(str);
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
	
	private File getFileFromFilePath(final String filePath){
		File resultFile = new File(filePath);
		if(resultFile.exists()){
			return resultFile;
		}else{
			return new File(FileStringGetter.class.getClassLoader().getResource(filePath).getFile());		
		}
	}

}
