package com.epam.predinedFunctionalInterface;

import com.epam.Product;
import com.epam.Data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FunctionalInterfaceTask2 {
	public static void main(String[] args) {

		Data data = new Data();
		List<Product> productList = data.getProducts();
		//Task-1
		BiConsumer<Product,String> print = (product,medium )->{
			if(medium.toLowerCase().equals("console")) {
				System.out.println(product);
			}
			else {
				File file = new File("C:\\Users\\Lavanya_Muvva\\HomeTasks\\Product.log");
			    try (FileWriter writer = new FileWriter(file, true)) {
			    	writer.write(product.toString() + System.lineSeparator());
			    } 
			    catch (IOException e) {
			    	System.out.println("An error occurred while writing to file: " + e.getMessage());
			    }
			}
		};
		print.accept(productList.get(9), "file");
		//Task-2
		Consumer<Product> gradeUpdate = (product) -> {
			if (product.getPrice() > 1000) {
				product.setGrade("Premium");
			}
		};
		for (Product p : productList) {
			gradeUpdate.accept(p);
		}
		System.out.println(productList);
		//Task-3
		Consumer<Product> nameUpdate = (product) -> {
			if (product.getPrice() > 3000) {
				product.setName(product.getName() + "*");
			}
		};
		for (Product p : productList) {
			nameUpdate.accept(p);
		}
		System.out.println(productList);
		//Task-4
		Consumer<Product> printSuffix = (product) -> {
			if (product.getName().endsWith("*")) {
				System.out.println(product.getName());
			}
		};
		for (Product p : productList) {
			printSuffix.accept(p);
		}
		//Task-5
		Random random = new Random();
		Supplier<Product> randomProduct = () -> {
			int randomIndex = random.nextInt(productList.size());
			return productList.get(randomIndex);
		};
		System.out.println(randomProduct.get());
		//Task-6
		Supplier<Integer> randomInt = () -> {
			return random.nextInt(1, 10000);
		};
		System.out.println(randomInt.get());

	}
}