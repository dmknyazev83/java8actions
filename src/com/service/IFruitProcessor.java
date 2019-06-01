package com.service;

import java.util.List;

import com.dto.Fruit;
import com.service.transpiration.ITranspiration;

public interface IFruitProcessor {
	
	List<Fruit> getFruitWithChanges(ITranspiration transpiration, int days, int lostWeight);

}
