package com.epam.predinedFunctionalInterface;
import java.util.List;
import com.epam.Product;
import com.epam.Data;
class FunctionalInterfaceTask3 {
	public static void main(String[] args) {
		Data products = new Data();
		List<Product> productList = products.getProducts();

		System.out.println(productList.stream().map(Product::getPrice).reduce(0, (value1, value2) -> value1 + value2));

		System.out.println(productList.stream().filter(product -> product.getPrice() > 1000)
				.map(Product::getPrice)
				.reduce(0, (value1, value2) -> value1 + value2));

		System.out.println(productList.stream().filter(product -> product.getCategory().equals("Electronics"))
				.map(Product::getPrice)
				.reduce(0, (value1, value2) -> value1 + value2));
		
		productList.stream().filter(product -> product.getPrice()>1000)
				.filter(product -> product.getCategory().equals("Electronics"))
				.forEach(System.out::println);

	}

}