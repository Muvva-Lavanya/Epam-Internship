package com.epam.predinedFunctionalInterface;
import java.util.List;
import java.util.function.Predicate;
import com.epam.Product;
import com.epam.Data;
public class FunctionalInterfaceTask1
{
	public static void main(String[] args) {
		Data products=new Data();
		List<Product> productList=products.getProducts();
		
		Predicate<Integer> priceGreaterThan1000=(price)->price>1000;
		Predicate<String> belongToElectronics=(category)->category.equals("Electronics");
		
		System.out.println("\nProducts with price>1000 : ");
		for(Product product:productList) {
		if(priceGreaterThan1000.test(product.getPrice())) {
		System.out.println(product);
		}
		}

		System.out.println("\nElectronic products : ");
		for(Product product:productList) {
		if(belongToElectronics.test(product.getCategory())) {
		System.out.println(product);
		}
		}

		System.out.println("\nElectronic products with price greater than 1000 : ");
		for(Product product:productList) {
		if(belongToElectronics.test(product.getCategory()) && priceGreaterThan1000.test(product.getPrice())) {
		System.out.println(product);
		}
		}

		System.out.println("\nproducts which are electronics or with price greater than 1000 : ");
		for(Product product:productList) {
		if(belongToElectronics.test(product.getCategory()) || priceGreaterThan1000.test(product.getPrice())) {
		System.out.println(product);
		}

		}

		System.out.println("\nElectronic products with price less than 1000 : ");
		for(Product product:productList) {
		if(belongToElectronics.test(product.getCategory()) && Predicate.not(priceGreaterThan1000).test(product.getPrice())) {
		System.out.println(product);
		}

		}
		
		
	}
}