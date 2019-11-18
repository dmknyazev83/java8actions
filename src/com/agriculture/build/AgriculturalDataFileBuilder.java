package com.agriculture.build;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dto.agriculture.AgriculturalDataBuilder;
import com.dto.agriculture.AgricultureData;
import com.dto.agriculture.IAgriculturalDataBuilder;

public class AgriculturalDataFileBuilder implements IBuildAgriculturalData {
	
	private String file;
	private IAgriculturalDataBuilder builder;
	
	public AgriculturalDataFileBuilder(final String file){
		this.file = file;
		this.builder = new AgriculturalDataBuilder();
	}

	@Override
	public List<AgricultureData> buildAgriculturalDataList() {
		List<AgricultureData> res  = Collections.emptyList();
		URL fileUrl = this.getClass().getClassLoader().getResource(this.file);
		try(Stream<String> fileStremContent = Files.lines(Paths.get(fileUrl.toURI()))){			
			res = fileStremContent.map(item -> builder.build(item)).collect(Collectors.toList());			
		}catch(URISyntaxException | IOException  e){
			e.printStackTrace();
		}		
		
		return res;
	}

}
