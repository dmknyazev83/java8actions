package com.dto;

public enum Color {
	
	RED(1), YELLOW(2), GREEN(3), ORANGE(4);
	
	private int val;
	
	private Color(int val){
		this.val = val;
	}
	
	public int getColor(){
		return this.val;
	}
	
	public static Color getByIntValue(int value){
		for(Color color : Color.values()){
			if (color.getColor() == value){
				return color;
			}
		}
		
		throw new IllegalStateException(
				String.format("Impossible to find color for value %s", value));
	}

}
