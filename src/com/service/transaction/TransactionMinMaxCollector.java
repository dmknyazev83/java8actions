package com.service.transaction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.dto.transaction.TransactionItem;
import com.dto.transaction.TransactionMinMax;

public class TransactionMinMaxCollector implements Collector<TransactionItem, List<TransactionItem>, TransactionMinMax> {

	@Override
	public Supplier<List<TransactionItem>> supplier() {
		
		return ArrayList::new;
	}

	@Override
	public BiConsumer<List<TransactionItem>, TransactionItem> accumulator() {
		// TODO Auto-generated method stub
		return (list, item) -> {
			System.out.println(item);
			list.add(item);
			};
	}

	@Override
	public BinaryOperator<List<TransactionItem>> combiner() {
		
		return (item1, item2) -> {
			System.out.println("call combiner");
			item1.addAll(item2); 
			return item1;
			};
	}

	@Override
	public Function<List<TransactionItem>, TransactionMinMax> finisher() {
		
		return (trItemList) -> {
			
			Optional<TransactionItem> max = trItemList.stream().collect(Collectors.maxBy(Comparator.comparingLong(TransactionItem::getAmount)));
			Optional<TransactionItem> min = trItemList.stream().min(Comparator.comparingLong(TransactionItem::getAmount));
			
			return new TransactionMinMax(max.orElse(null), min.orElse(null));
		};
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		Set<java.util.stream.Collector.Characteristics> characteristics = new HashSet<java.util.stream.Collector.Characteristics>();
		characteristics.add(java.util.stream.Collector.Characteristics.CONCURRENT);
		return characteristics;
	}

}
