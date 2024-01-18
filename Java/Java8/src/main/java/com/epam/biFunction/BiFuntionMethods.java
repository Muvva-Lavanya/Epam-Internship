package com.epam.biFunction;

import java.util.function.BiFunction;

import com.epam.Product;

public class BiFuntionMethods
{

	public Product createProduct(String productName, int productPrice) {
		BiFunction<String,Integer,Product> createProduct = (name,price)->{ 
			Product product = new Product();
			product.setName(name);
			product.setPrice(price);
			return product;
		};
		Product product=createProduct.apply(productName, productPrice);
		return product;
	}
	
}