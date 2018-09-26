package com.file.work;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileStringGetter {
	
	public void processFile(final String filePath, IFileStringsWriter stringWriter){
		
		try(BufferedReader br = 
				new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))){
			
			final String str = stringWriter.proceedString(br);
			System.out.println(str);
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}

}
