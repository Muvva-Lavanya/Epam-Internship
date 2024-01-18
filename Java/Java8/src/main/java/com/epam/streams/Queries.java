package com.epam.streams;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.epam.Employee;

public class Queries
{
	public Map<String,Long> countNoOfMaleAndFemaleEmployees(List<Employee> employeeList)
	{
		return employeeList.stream().collect(Collectors.groupingBy(e -> e.getGender(),Collectors.counting()));
	}
	public Stream<String> namesOfAllDepartments1(List<Employee> employeeList) {
		return employeeList.stream().map(e -> e.getDepartment())
				.distinct();
	}
	public Map<String,Double> averageAgeOfMaleAndFemale(List<Employee> employeeList) {
		return employeeList.stream().collect(Collectors.groupingBy(e -> e.getGender(),Collectors.averagingInt(e ->e.getAge())));
	}
	public Optional<Employee> highestPaidEmployee(List<Employee> employeeList) {
		return employeeList.stream().max(Comparator.comparing(e ->e.getSalary()));
	}
	public Stream<Object> nameOfEmployeesAfter2015(List<Employee> employeeList) {
		return employeeList.stream().filter(e -> e.getYearOfJoining()>=2015).map(e -> e.getName());
	}
	public Map<Object, Long> NoOfEmployeeInEachDepartment(List<Employee> employeeList) {
		return employeeList.stream().collect(Collectors.groupingBy(e -> e.getDepartment(),Collectors.counting()));
	}
	public Map<Object, Double> averageSalaryOfEachDepartment(List<Employee> employeeList) {
		return employeeList.stream().collect(Collectors.groupingBy(e -> e.getDepartment(),Collectors.averagingDouble(e -> e.getSalary())));
		
	}
	public Optional<Employee> youngestMaleEmployee(List<Employee> employeeList) {
		return employeeList.stream().filter(e -> e.getDepartment().equals("Product Development")).min(Comparator.comparing(e -> e.getAge()));
	}
	public Optional<Employee> highestExperienceEmployee(List<Employee> employeeList) {
		return employeeList.stream().min(Comparator.comparing(e ->e.getYearOfJoining()));
	}
	public Map<Object, Long> countMaleAndFemaleEmployeesInSalesDept(List<Employee> employeeList) {
		return employeeList.stream().filter(e -> e.getDepartment().equals("Sales And Marketing")).collect(Collectors.groupingBy(e -> e.getGender(),Collectors.counting()));
	}
	public Map<Object, Double> maleFemaleAverageSalary(List<Employee> employeeList) {
		return employeeList.stream().collect(Collectors.groupingBy(e -> e.getGender(),Collectors.averagingDouble(e ->e.getSalary())));
	}
	public Map<Object, Set<Object>> namesInEachDepartment(List<Employee> employeeList) {
		return employeeList.stream().collect(Collectors.groupingBy(e -> e.getDepartment(),Collectors.mapping(e -> e.getName(),Collectors.toSet())));
		
	}
	public double totalSalary(List<Employee> employeeList) {
		return employeeList.stream().mapToDouble(e -> e.getSalary()).sum();
	}
	public OptionalDouble averageSalary(List<Employee> employeeList) {
		return employeeList.stream().mapToDouble(e -> e.getSalary()).average();
	}
	public Stream<Object> youngerEmployee(List<Employee> employeeList) {
		return employeeList.stream().filter(e -> e.getAge()<=25).map(e -> e.getName());
	}
	public Stream<Object> elderEmployee(List<Employee> employeeList) {
		return employeeList.stream().filter(e -> e.getAge()>25).map(e -> e.getName());
	}
	public Optional<Employee> oldestEmployee(List<Employee> employeeList) {
		return employeeList.stream().max(Comparator.comparing(e -> e.getAge()));
	}
}