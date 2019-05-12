package com.service.transpiration;

import com.dto.Fruit;
import com.dto.TranspirationIndex;

public interface ITranspiration {
	
	TranspirationIndex calculate(Fruit before, Fruit after, int days);

}
