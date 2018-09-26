package com.service;

import java.util.List;

import com.dto.Fruit;

public interface IFruitService {
	
	List<Fruit> generateFruitCollection();
	
	List<Fruit> generateFruitCollection(int size);

}
