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
		fruitProcessor.getFruitWithChanges((Fruit before, Fruit after, int days) -> {return new TranspirationIndex(3, 5.7);});
		
		final ITranspiration transpiration = new TranspirationImpl();
		fruitProcessor.getFruitWithChanges(transpiration);
	}

}
