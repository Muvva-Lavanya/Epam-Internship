package com.epam.lambdas;

import java.util.List;

import com.epam.Data;
import com.epam.Employee;

public class Lambdas {

	public static void main(String[] args) {
		Data data = new Data();
		LambdaMethods methods = new LambdaMethods();
		List<Integer> numberList = data.getNumbers();
		List<Employee> employeeList = data.getEmployeeDetails();

		String name = "tomato";
		if (methods.isPalindromeChecker(name))
			System.out.println(name + " is a palindrome");
		else
			System.out.println(name + " is not a palindrome");

		System.out.println("\nSecond Largest Number:" + methods.findSecondBiggestNumber(numberList));

		String string1 = "Hello";
		String string2 = "loHel";
		if (methods.isRotationOfEachOther(string1, string2))
			System.out.println("\n" + string1 + " " + string2 + "are rotations of each other");
		else
			System.out.println("\n" + string1 + " " + string2 + "are not rotations of each other");

		System.out.println(
				"\nNumbers sorted in descending order using comparator:\n" + methods.sortNumbersInDesc(numberList));

		System.out
				.println("\nEmployees sorted by name using comparator:\n" + methods.sortEmployeesByName(employeeList));

		System.out.println(
				"\nNumbers sorted in descending order using TreeSet:\n" + methods.sortNumInDescTreeset(numberList));

		System.out.println(
				"\nEmployees sorted by name using TreeSet:\n" + methods.sortEmployeesByNameTreeset(employeeList));

		System.out.println("\nNumbers sorted in descending order using Treemap:\n"
				+ methods.sortNumInDescTreemap(numberList).keySet());

		System.out.println("\nEmployees sorted by name using Treemap:\n"
				+ methods.sortEmployeesByNameTreemap(employeeList).keySet());

		System.out.println("\nEmployees sorted by name using Collections:\n"
				+ methods.sortEmployeesNamesByCollections(employeeList));

	}

}
