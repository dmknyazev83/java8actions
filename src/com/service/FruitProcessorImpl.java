package com.service;

import java.util.ArrayList;
import java.util.List;

import com.dto.Fruit;
import com.dto.TranspirationIndex;
import com.service.transpiration.ITranspiration;

public class FruitProcessorImpl implements IFruitProcessor {
	
	private IFruitService service;
	
	public FruitProcessorImpl() {
		service = new FruitService();
	}

	public List<Fruit> getFruitWithChanges(ITranspiration transpiration){
		List<Fruit> harvestList = service.generateFruitCollection();
		List<Fruit> warehouseList = new ArrayList<Fruit>();
		
		for(Fruit fruit : harvestList){
			final Fruit wareFruit = new Fruit(fruit.getWeight() - 1, fruit.getColor(), fruit.getCountry());
			warehouseList.add(wareFruit);
		}
		
		int days = 3;
		
		for(int i = 0; i< harvestList.size(); i++){
			TranspirationIndex index = transpiration.calculate(harvestList.get(i), warehouseList.get(i), days);
			//TranspirationIndex index = transpiration.calculate(harvestList.get(i), warehouseList.get(i));
			harvestList.get(i).getFruitTranspirationStat().put(days, index);
		}
		
		return harvestList;		
	}

}
