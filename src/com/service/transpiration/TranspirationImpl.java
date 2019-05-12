package com.service.transpiration;

import com.dto.Fruit;
import com.dto.TranspirationIndex;

public class TranspirationImpl implements ITranspiration {


	public TranspirationIndex calculate(Fruit before, Fruit after, int days) {
		double speed = (before.getWeight() - after.getWeight())/(days*before.getWeight());
		TranspirationIndex index = new TranspirationIndex(days, speed);
		return index;
	}
	
//	public TranspirationIndex calculate(Fruit before, Fruit after){
//		double speed = (before.getWeight() - after.getWeight())/(3*before.getWeight());
//		TranspirationIndex index = new TranspirationIndex(3, speed);
//		return index;
//	}

}
