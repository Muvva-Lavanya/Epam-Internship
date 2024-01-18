package com.epam;

//import com.epam.Employee1;
import java.util.*;

import java.util.Map.Entry;

public class Task2 {

	public static void main(String args[]) {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

		Methods m1 = new Methods();
		ArrayList<Integer> employeeCount = new ArrayList<Integer>();
		employeeCount = m1.countNoOfMaleAndFemaleEmployees(employeeList);
		System.out.print("Number of Male Employees are : " + employeeCount.get(0));
		System.out.println(",Number of Female Employees are : " + employeeCount.get(1));
		System.out.println();

		System.out.println("Name of all departments are :" + m1.namesOfAllDepartments1(employeeList));
		System.out.println();

		ArrayList<Float> averageAge = new ArrayList<Float>();
		averageAge = m1.averageAgeOfMaleAndFemale(employeeList);
		System.out.print("Average age of Male Employees is : " + averageAge.get(0) / employeeCount.get(0));
		System.out.println(" ,Average age of Female Employees is : " + averageAge.get(1) / employeeCount.get(1));
		System.out.println();

		System.out.println("Highest Salary Employee Name is : " + employeeList.get(m1.highestPaidEmployee(employeeList)));
		System.out.println();

		System.out.println("Employees who joined after 2015 are :" + m1.nameOfEmployeesAfter2015(employeeList));
		System.out.println();

		HashMap<String, Integer> employeeCountInEachDepartment = new HashMap<String, Integer>();
		employeeCountInEachDepartment = m1.NoOfEmployeeInEachDepartment(employeeList);
		System.out.print("Number of employees in each department are :");
		System.out.println(m1.NoOfEmployeeInEachDepartment(employeeList));
		System.out.println();

		HashMap<String, Double> averageSalaryOfEachDept = new HashMap<String, Double>();
		averageSalaryOfEachDept = m1.averageSalaryOfEachDepartment(employeeList);
		for (Entry<String, Double> e2 : averageSalaryOfEachDept.entrySet()) {
			averageSalaryOfEachDept.put(e2.getKey(), e2.getValue() / employeeCountInEachDepartment.get(e2.getKey()));
		}

		System.out.println("Average salary of each department is :"+averageSalaryOfEachDept);
		System.out.println();

		System.out.println("Youngest employee details are :"+employeeList.get(m1.youngestMaleEmployee(employeeList)));
		System.out.println();

		System.out.println("Highest Experience Employee Name is : "+ employeeList.get(m1.highestExperienceEmployee(employeeList)));
		System.out.println();

		ArrayList<Integer> countMaleAndFemaleEmployeesInSales = new ArrayList<Integer>();
		countMaleAndFemaleEmployeesInSales = m1.countMaleAndFemaleEmployeesInSalesDept(employeeList);
		System.out.print("Number of Male Employees in Sales And Marketing are : " + countMaleAndFemaleEmployeesInSales.get(0));
		System.out.println(",Number of Female Employees in Sales And Marketing are : "+ countMaleAndFemaleEmployeesInSales.get(1));
		System.out.println();

		ArrayList<Float> maleFemaleAverageSal = new ArrayList<Float>();
		maleFemaleAverageSal = m1.maleFemaleAverageSalary(employeeList);
		System.out.print("Average Salary of Male Employees is : " + maleFemaleAverageSal.get(0));
		System.out.println(" ,Average Salary of Female Employees is : " + maleFemaleAverageSal.get(1));
		System.out.println();

		System.out.println("Employee names from each department are : "+m1.namesInEachDepartment(employeeList));
		System.out.println();

		int totalSalary = m1.totalSalary(employeeList);
		float averageSalary = totalSalary / employeeList.size();
		System.out.print("Total Salary is : " + totalSalary);
		System.out.println(",Average Salary is : " + averageSalary);
		System.out.println();

		System.out.println("Younger employeees(<=25) are :" + m1.youngerEmployee(employeeList));
		System.out.println("Elder employees(>25) are :" + m1.elderEmployee(employeeList));
		System.out.println();

		int o1 = m1.oldestEmployee(employeeList);
		Employee e1 = employeeList.get(o1);
		System.out.println("Oldest Employee Name is : " + e1.getName());
		System.out.println("Oldest Employee Age is : " + e1.getAge());
		System.out.println("Oldest Employee Department is " + e1.getDepartment());
		System.out.println();
		
		
		//TASK-1
		ArrayList<Integer> list1=new ArrayList<Integer>();
		 for(int i=0;i<20;i++){
			list1.add((int)(Math.random() * 400) + 1);
		}
		Collections.sort(list1,new NumberComparator());
		System.out.println("Second Largest number in list is : "+list1.get(1));
		System.out.println();
		
		System.out.println("Sorting the numbers in reverse order using comparator"+list1);
		System.out.println();
		
		Collections.sort(employeeList,new EmployeeComparator());
		System.out.println("Sorting the employee names in alphabetical order using comparator"+employeeList);
		System.out.println();
		
		TreeSet<Integer> numbersSet=new TreeSet<Integer>(list1);
		System.out.println("Sorting numbers using treeset : "+numbersSet.descendingSet());
		System.out.println();
		
		
		TreeSet<Employee> employeeSet=new TreeSet<Employee>(new EmployeeComparator());
		employeeSet.addAll(employeeList);
		System.out.println("Sorting employee names using treeset : "+employeeSet);
		System.out.println();
		
		TreeMap<Integer,Integer> valuesMap=new TreeMap<Integer,Integer>(new NumberComparator());
		Integer temp=0;
		for(Integer value : list1)
		{
			valuesMap.put(value,temp);
			temp+=1;
		}
		System.out.print("Sorting values using treeMap : "+valuesMap.keySet());
		System.out.println();
		
		TreeMap<Employee,Integer> employeeMap=new TreeMap<Employee,Integer>(Collections.reverseOrder( new EmployeeComparator()));
		for(Employee emp : employeeList)
		{
			employeeMap.put(emp,emp.getId());
		}
		System.out.println("Sorting employee names using treemap : ");
		for(Employee emp : employeeMap.keySet())
		{
			System.out.println(emp.getId()+" - "+emp.getName());
		}
		System.out.println();
		
		Collections.sort(employeeList,Collections.reverseOrder(new EmployeeComparator()));
		System.out.println("Sorting employee names using collections : ");
		for(Employee emp : employeeList)
		{
			System.out.print(emp.getName()+" , ");
		}
		System.out.println();
		
	}
}