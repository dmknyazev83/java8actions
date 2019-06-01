package com.service;

import com.dto.Fruit;

/**
 * Functional interface to create instance of {@link Fruit}
 */
public interface IFruitCreator<T, U, N, R> {
	
	R create(T t, U u, N n);

}
