package com.dto.agriculture;

public class AgricultureData {
	
	private String locationCode;
	private String indicator;
	private String culture;
	private String measureUnit;
	private String frequency;
	private String time;
	private String value;
	
	
	public AgricultureData(String locationCode, String indicator, 
			String culture, String measureUnit, 
			String frequency,String time, 
			String value) {
		super();
		this.locationCode = locationCode;
		this.indicator = indicator;
		this.culture = culture;
		this.measureUnit = measureUnit;
		this.frequency = frequency;
		this.time = time;
		this.value = value;
	}


	public String getLocationCode() {
		return locationCode;
	}


	public String getIndicator() {
		return indicator;
	}


	public String getCulture() {
		return culture;
	}


	public String getMeasureUnit() {
		return measureUnit;
	}


	public String getFrequency() {
		return frequency;
	}


	public String getTime() {
		return time;
	}


	public String getValue() {
		return value;
	}
}
