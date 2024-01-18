package com.epam.lambdas;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import com.epam.Employee;

public class LambdaMethods {

	public boolean isPalindromeChecker(String str) {
		IsPalindrome palindrome = string -> {
			String reversedName = new StringBuilder(string).reverse().toString();
			return string.equals(reversedName);
		};
		return palindrome.isPalindrome(str);
	}

	public int findSecondBiggestNumber(List<Integer> numberList) {
		Collections.sort(numberList, (num1, num2) -> num2 - num1);
		return numberList.get(1);
	}

	public boolean isRotationOfEachOther(String s1, String s2) {
		IsRotation rotated = (string1, string2) -> {
			String rotate = string1 + string1;
			return rotate.contains(string2);
		};
		return rotated.isRotation(s1, s2);
	}

	public List<Integer> sortNumbersInDesc(List<Integer> numberList) {
		Collections.sort(numberList, (number1, number2) -> number2 - number1);
		return numberList;
	}

	public List<Employee> sortEmployeesByName(List<Employee> employeeList) {
		Collections.sort(employeeList, Comparator.comparing(Employee::getName));
		return employeeList;
	}

	public Set<Integer> sortNumInDescTreeset(List<Integer> numberList) {
		SortedSet<Integer> numberSet = new TreeSet<>((number1, number2) -> number2 - number1);
		numberSet.addAll(numberList);
		return numberSet;
	}

	public Set<Employee> sortEmployeesByNameTreeset(List<Employee> employeeList) {
		SortedSet<Employee> employeeSet = new TreeSet<>(Comparator.comparing(Employee::getName));
		employeeSet.addAll(employeeList);
		return employeeSet;
	}

	public Map<Integer, Integer> sortNumInDescTreemap(List<Integer> numberList) {
		SortedMap<Integer, Integer> numberMap = new TreeMap<>((number1, number2) -> number1 - number2);
		for (Integer counter : numberList) {
			numberMap.put(counter, 10);
		}
		return numberMap;
	}

	public Map<Employee, Integer> sortEmployeesByNameTreemap(List<Employee> employeeList) {
		TreeMap<Employee, Integer> employeeMap = new TreeMap<>(Comparator.comparing(Employee::getName).reversed());
		for (Employee employee : employeeList) {
			employeeMap.put(employee, employee.getId());
		}
		return employeeMap;
	}

	public void threadsWithRunnable() {
		Thread thread = new Thread(() -> {
			for (int counter = 0; counter < 10; counter++)
				System.out.println(counter);
		});
		thread.start();
	}

	public List<Employee> sortEmployeesNamesByCollections(List<Employee> employeeList) {
		Collections.sort(employeeList, Collections.reverseOrder((o1, o2) -> o1.getName().compareTo(o2.getName())));
		
		return employeeList;
	}

}
