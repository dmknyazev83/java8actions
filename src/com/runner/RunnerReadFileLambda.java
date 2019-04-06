package com.runner;

import java.io.BufferedReader;
import com.file.work.FileStringGetter;

public class RunnerReadFileLambda {

	public static void main(String[] args) {
		final String filePath = "/home/dima/Unpacked_files/GoverningPersons.txt";
		
		FileStringGetter fsg = new FileStringGetter();
		//fsg.processFile(filePath, (BufferedReader br) -> {return br.readLine();});
		//fsg.processFile(filePath, (BufferedReader br) -> {return br.readLine() + br.readLine();});
		fsg.processFile(filePath, (BufferedReader br) -> {
			int size = 0;
			while(br.readLine() != null){
				size++;
			}
			return String.format("Size of processd file is %d lines", size);
		});

	}

}
