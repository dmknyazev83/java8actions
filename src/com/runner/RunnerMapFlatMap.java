package com.runner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RunnerMapFlatMap {

	public static void main(String[] args) {
		
		final Integer[] initialArr = {2 , 2};
		
		List<?> items = Arrays.stream(initialArr).map(item -> {
			List<String> listItems = new ArrayList<String>(item);
			for(int i = 0; i < item; i++ ){
				listItems.add("Added string " + i);
			}
			return listItems;
			}).collect(Collectors.toList());
		
		for(Object obj : items){
			System.out.println("iteration");
			if(obj instanceof List){
				List objList = (List)obj;
				objList.stream().forEach(item -> System.out.println(item));
			}
		}
		
		System.out.println();
		
		List<?> itemsFlatMap = Arrays.stream(initialArr).flatMap(item -> {
			List<String> listItems = new ArrayList<String>(item);
			for(int i = 0; i < item; i++ ){
				listItems.add("Added string " + i);
			}
			return listItems.stream();
			}).collect(Collectors.toList());
		System.out.println(itemsFlatMap.size());
		
		itemsFlatMap.stream().forEach(item -> System.out.println(item));

	}

}
