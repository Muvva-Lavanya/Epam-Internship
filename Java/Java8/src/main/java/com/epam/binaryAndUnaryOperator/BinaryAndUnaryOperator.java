package com.epam.binaryAndUnaryOperator;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BinaryAndUnaryOperator {
	public static void main(String[] args) {
		BinaryAndUnaryOperatorMethods object1 = new BinaryAndUnaryOperatorMethods();
		Predicate<Integer> predicate = (number) -> object1.isPrime(number);
		System.out.println(predicate.test(19));
		Consumer<Integer> consumer = number -> System.out.println("Square of a number is : " + (number * number));
		consumer.accept(5);
		Random random=new Random();
		Supplier<Integer> supplier = () -> random.nextInt(0,5000);
		System.out.println((supplier.get()));
		
	}
}
