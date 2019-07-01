package com.runner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class StreamFilteringTest {
	
	@Test
	public void filteringStream(){
		
		List<Long> numbers = Arrays.asList(2L, 5L, 3L, 12L, 6L, 3L, 4L, 2L);
		List<Long> evenNmbers = numbers.stream().filter(item -> item%2 == 0).distinct().collect(Collectors.toList());
		evenNmbers.stream().forEach(item -> Assert.assertTrue(item % 2 <= 0));
		
		List<Long> limitedList = numbers.stream().limit(5).collect(Collectors.toList());
		Assert.assertTrue(limitedList.size() == 5);
	}

}
