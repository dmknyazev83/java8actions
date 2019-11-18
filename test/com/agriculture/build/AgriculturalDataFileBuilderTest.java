package com.agriculture.build;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dto.agriculture.AgricultureData;

public class AgriculturalDataFileBuilderTest {
	
	private IBuildAgriculturalData dataBuilder;
	
	@Before
	public void init(){
		final String file = "Unpacked_files/DP_LIVE_21102019130847515.csv";
		dataBuilder = new AgriculturalDataFileBuilder(file);
	}
	
	@Test
	public void buildAgriculturalDataListTest(){
		List<AgricultureData> actualList = dataBuilder.buildAgriculturalDataList();
		Assert.assertTrue(!actualList.isEmpty());
		AgricultureData anyData = actualList.stream().findAny().orElseGet(null);
		Assert.assertNotNull(anyData);
		Assert.assertNotNull(anyData.getCulture());
		Assert.assertNotNull(anyData.getFrequency());
		Assert.assertNotNull(anyData.getIndicator());
		Assert.assertNotNull(anyData.getLocationCode());
		Assert.assertNotNull(anyData.getMeasureUnit());
		Assert.assertNotNull(anyData.getTime());
		Assert.assertNotNull(anyData.getValue());
	}

}
