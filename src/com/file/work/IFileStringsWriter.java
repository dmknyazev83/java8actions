package com.file.work;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface IFileStringsWriter {
	
	String proceedString(final BufferedReader br) throws IOException;

}
