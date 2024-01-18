package com.epam;

import java.util.*;
public class Methods {
	Employee e1;
	int i = 0, j = 0;
	public ArrayList<Integer> countNoOfMaleAndFemaleEmployees(ArrayList<Employee> employeeList) {
		int maleEmployees = 0, femaleEmployees = 0;
		for (Employee i : employeeList) {
			if (i.getGender() == "Male") {
				maleEmployees += 1;
			} else {
				femaleEmployees += 1;
			}
		}
		ArrayList<Integer> employeeCount = new ArrayList<Integer>();
		employeeCount.add(maleEmployees);
		employeeCount.add(femaleEmployees);
		return employeeCount;
	}
	public ArrayList<String> namesOfAllDepartments1(ArrayList<Employee> employeeList) {
		ArrayList<String> departmentNames = new ArrayList<String>();
		for (Employee i : employeeList)
		{
			String s = i.getDepartment();
			if (departmentNames.contains(s)) {
				continue;
			} else {
				departmentNames.add(i.getDepartment());
			}
		}
		return departmentNames;
	}
	public ArrayList<Float> averageAgeOfMaleAndFemale(ArrayList<Employee> employeeList) {
		ArrayList<Float> averageAge = new ArrayList<Float>();
		float maleAvg = 0, femaleAvg = 0;
		for (Employee i : employeeList)
		{
			if (i.getGender() == "Male") {
				maleAvg += i.getAge();
			} else {
				femaleAvg += i.getAge();
			}
		}
		averageAge.add(maleAvg);
		averageAge.add(femaleAvg);
		return averageAge;
	}
	public int highestPaidEmployee(ArrayList<Employee> employeeList) {
		double maxSal = 0;
		int o1 = 0;
		for (i = 0; i < employeeList.size(); i++) {
			e1 = employeeList.get(i);
			if (e1.getSalary() >= maxSal) {
				maxSal = e1.getSalary();
				o1 = i;
			}
		}
		return o1;
	}
	public ArrayList<String> nameOfEmployeesAfter2015(ArrayList<Employee> employeeList) {
		ArrayList<String> empNames = new ArrayList<String>();
		for(Employee i : employeeList)
		{
			if (i.getYearOfJoining() > 2015) {
				empNames.add(e1.getName());
			}
		}
		return empNames;
	}
	public HashMap<String, Integer> NoOfEmployeeInEachDepartment(ArrayList<Employee> employeeList) {
		HashMap<String, Integer> h1 = new HashMap<String, Integer>();
		String depName;
		for(Employee i : employeeList)
		{
			depName = i.getDepartment();
			if (h1.containsKey(depName)) {
				h1.put(depName, h1.get(depName) + 1);
			} else {
				h1.put(depName, 1);
			}
		}
		return h1;
	}
	public HashMap<String, Double> averageSalaryOfEachDepartment(ArrayList<Employee> employeeList) {
		HashMap<String, Double> h2 = new HashMap<String, Double>();
		String depName;
		for(Employee i : employeeList)
		{
			depName = i.getDepartment();
			if (h2.containsKey(depName)) {
				h2.put(depName, h2.get(depName) + e1.getSalary());
			} else {
				h2.put(depName, 0.0);
			}
		}
		return h2;
	}
	public int youngestMaleEmployee(ArrayList<Employee> employeeList) {
		int o1 = 0;
		int minAge = 9999;
		for (i = 0; i < employeeList.size(); i++) {
			e1 = employeeList.get(i);
			if (e1.getDepartment() == "Product Development") {
				if (e1.getAge() <= minAge) {
					minAge = e1.getAge();
					o1 = i;
				}
			}
		}
		return o1;
	}
	public int highestExperienceEmployee(ArrayList<Employee> employeeList) {
		int minYearOfJoin = 100000;
		int o1 = 0;
		for (i = 0; i < employeeList.size(); i++) {
			e1 = employeeList.get(i);
			if (e1.getYearOfJoining() <= minYearOfJoin) {
				minYearOfJoin = e1.getYearOfJoining();
				o1 = i;
			}
		}
		return o1;
	}
	public ArrayList<Integer> countMaleAndFemaleEmployeesInSalesDept(ArrayList<Employee> employeeList) {
		ArrayList<Integer> countMaleAndFemaleEmployeesInSales = new ArrayList<Integer>();
		int countMales1 = 0;
		int countFemales1 = 0;
		for(Employee i : employeeList)
		{
			if (i.getDepartment() == "Sales And Marketing") {
				if (i.getGender() == "Male") {
					countMales1 += 1;
				} else {
					countFemales1 += 1;
				}
			}
		}
		countMaleAndFemaleEmployeesInSales.add(countMales1);
		countMaleAndFemaleEmployeesInSales.add(countFemales1);
		return countMaleAndFemaleEmployeesInSales;
	}
	public ArrayList<Float> maleFemaleAverageSalary(ArrayList<Employee> employeeList) {
		ArrayList<Float> maleFemaleAverageSal = new ArrayList<Float>();
		float maleSal = 0, femaleSal = 0;
		int m = 0, f = 0;
		for(Employee i : employeeList)
		{
			if (i.getGender() == "Male") {
				maleSal += e1.getSalary();
				m += 1;
			} else {
				femaleSal += e1.getSalary();
				f += 1;
			}
		}
		maleFemaleAverageSal.add(maleSal / m);
		maleFemaleAverageSal.add(femaleSal / f);
		return maleFemaleAverageSal;
	}
	public HashMap<String, String> namesInEachDepartment(ArrayList<Employee> employeeList) {
		HashMap<String, String> namesOfEmployeesInEachDepartment = new HashMap<String, String>();
		String depName;
		for(Employee i : employeeList)
		{
			depName = i.getDepartment();
			if (namesOfEmployeesInEachDepartment.containsKey(depName)) {
				namesOfEmployeesInEachDepartment.put(depName,
						namesOfEmployeesInEachDepartment.get(depName) + e1.getName() + ",");
			} else {
				namesOfEmployeesInEachDepartment.put(depName, e1.getName());
			}
		}
		return namesOfEmployeesInEachDepartment;
	}
	public int totalSalary(ArrayList<Employee> employeeList) {
		int totalSalary = 0;
		for(Employee i : employeeList)
		{
			totalSalary += i.getSalary();
		}
		return totalSalary;
	}
	public ArrayList<String> youngerEmployee(ArrayList<Employee> employeeList) {
		ArrayList<String> younger = new ArrayList<String>();
		for(Employee i : employeeList)
		{
			if (i.getAge() <= 25) {
				younger.add(i.getName());
			}
		}
		return younger;
	}
	public ArrayList<String> elderEmployee(ArrayList<Employee> employeeList) {
		ArrayList<String> elder = new ArrayList<String>();
		for(Employee i : employeeList)
		{
			if (i.getAge() > 25) {
				elder.add(i.getName());
			}
		}
		return elder;
	}
	public int oldestEmployee(ArrayList<Employee> employeeList) {
		int maxAge = 0;
		int o1 = 0;
		for (i = 0; i < employeeList.size(); i++) {
			e1 = employeeList.get(i);
			if (e1.getAge() >= maxAge) {
				maxAge = e1.getAge();
				o1 = i;
			}
		}
		return o1;
	}

}