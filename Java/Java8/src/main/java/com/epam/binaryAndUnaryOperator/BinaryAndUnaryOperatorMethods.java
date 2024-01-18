package com.epam.binaryAndUnaryOperator;

public class BinaryAndUnaryOperatorMethods {

	public boolean isPrime(Integer x) {
		int count = 0;
		boolean result=false;
		if (x < 2) {
			result=false;
		} else {
			for (int counter = 3; counter < x; counter++) {
				if (x % counter == 0) {
					count += 1;
				}
			}
		}
		if (count == 0) {
			result=true;
		} else {
			result=false;
		}
		return result;
	}

}
