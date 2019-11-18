package com.dto.agriculture;

public class AgriculturalDataBuilder implements IAgriculturalDataBuilder {

	@Override
	public AgricultureData build(String agricultureData) {
		String[] agriData = agricultureData.split(",");
		if(agriData.length < 7){
			throw new IllegalStateException("Less tha 7 items in string");
		}
		AgricultureData data = new AgricultureData(agriData[0], agriData[1],
				agriData[2], agriData[3],
				agriData[4], agriData[5],
				agriData[6]);
		return data;
	}

}
