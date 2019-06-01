package com.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.dto.Fruit;

import junit.framework.Assert;

public class FruitServiceTest {
	
	private IFruitService fruitService;
	
	@Before
	public void init(){
		fruitService = new FruitService();
	}
	
	@Test
	public void generateFruitCollectionTest(){
		final List<Fruit> fruits = fruitService.generateFruitCollection(2);
		Assert.assertEquals(2, fruits.size());
		
		Fruit f0 = fruits.get(0);
		Assert.assertTrue(f0.getWeight() > 0);
		Assert.assertNotNull(f0.getColor());
		Assert.assertNotNull(f0.getCountry());
		Assert.assertTrue(f0.getFruitTranspirationStat().size() <= 0);		
	}

}
