package com.runner;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;


public class StreamMapTest {
	
	@Test
	public void streamMapSimple(){
		
		List<String> list = Arrays.asList("Army", "Face", "Backet", "House");
		
		Set<Integer> uniqueNumbers =  list.stream().map(str -> {
			final Random rand = new Random(5L);
			return str.length() * rand.nextInt();
		}).filter(v -> v > 0).collect(Collectors.toSet());
		
		Assert.assertTrue(uniqueNumbers.size() > 0);		
	}
	
	@Test
	public void streamManyListsToMap(){
		List<Integer> listPositive = Arrays.asList(1, 5);
		List<Integer> listNegative = Arrays.asList(-3, -2);
		
		listPositive.stream().map(posItem -> 
              listNegative.stream().map(negatItem -> {
     	                 final Integer[] res = new Integer[2];
     	                 res[0] = posItem;
     	                 res[1] = negatItem;
     	                 return res;
        }));
		
		List<Integer[]> doubleItems = listPositive.stream().flatMap(posItem -> 
		           listNegative.stream().map(negatItem -> {
		        	   final Integer[] res = new Integer[2];
		        	   res[0] = posItem;
		        	   res[1] = negatItem;
		        	   return res;
		           })).collect(Collectors.toList());
		
		doubleItems.stream().forEach(item -> {
			final StringBuilder sb = new StringBuilder();
			sb.append("[");
			Arrays.asList(item).forEach(n -> {
				sb.append(n); 
				sb.append(",");
			});


			sb.replace(sb.length()-1, sb.length(), "]");
			System.out.println(sb);		

		});
		
	}

}
