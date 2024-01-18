package com.epam.biFunction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;

import com.epam.Data;
import com.epam.Product;

public class Bifunction {
	public static void main(String[] args) {
		Data data = new Data();
		List<Product> productList = data.getProducts();
		BiFuntionMethods method = new BiFuntionMethods();
		//Task-1
		Product product = method.createProduct("Laptop", 2312);
		System.out.println(product);
		//Task-2
		BiFunction<Product, Integer, Integer> productCost = (prodct, quantity) -> prodct.getPrice() * quantity;
		Map<Product, Integer> productCart = new HashMap<>();
		Random random = new Random();
		for (Product product1 : productList) {
			int quantity = random.nextInt(1, 10);
			productCart.put(product1, quantity);

		}
		double cost = productCart.entrySet().stream()
				.mapToDouble(product2 -> productCost.apply(product2.getKey(), product2.getValue())).sum();
		System.out.println("Cart Cost is :" + cost);
	}

}
