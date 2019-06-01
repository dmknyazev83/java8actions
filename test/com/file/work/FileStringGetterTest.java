package com.file.work;

import java.io.BufferedReader;

import org.junit.Before;
import org.junit.Test;

public class FileStringGetterTest {

	private FileStringGetter filesStringGetter;
	
	@Before
	public void init(){
		filesStringGetter = new FileStringGetter();
	}
	
	@Test
	public void processFileOperationsLambdaTest(){
		String filePath = "Unpacked_files/list_of_collective_nouns-928j.csv";
		filesStringGetter.processFile(filePath, (BufferedReader br) -> {
			int size = 0;
			while(br.readLine() != null){
				size++;
			}
			return String.format("Size of processd file is %d lines", size);
		});
		filesStringGetter.processFile(filePath, (BufferedReader br) -> {return br.readLine();});
		filesStringGetter.processFile(filePath, (BufferedReader br) -> {return br.readLine() + br.readLine();});
	}

}
