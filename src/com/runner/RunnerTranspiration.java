package com.runner;

import com.dto.Fruit;
import com.dto.TranspirationIndex;
import com.service.FruitProcessorImpl;
import com.service.IFruitProcessor;
import com.service.transpiration.ITranspiration;
import com.service.transpiration.TranspirationImpl;

public class RunnerTranspiration {

	public static void main(String[] args) {
		IFruitProcessor fruitProcessor = new FruitProcessorImpl();
		fruitProcessor.getFruitWithChanges((Fruit before, Fruit after, int days) -> {return new TranspirationIndex(3, 5.7);}, 7, 1);
		
		final ITranspiration transpiration = new TranspirationImpl();
		fruitProcessor.getFruitWithChanges(transpiration, 3, 1);
		ITranspiration transpiration1 = (Fruit before, Fruit after, int days) -> new TranspirationIndex(78, 9.7);
		fruitProcessor.getFruitWithChanges(transpiration1, 6, 2);
	}

}
