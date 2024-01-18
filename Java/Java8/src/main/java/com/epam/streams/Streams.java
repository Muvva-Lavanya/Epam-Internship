package com.epam.streams;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.epam.Data;
import com.epam.Employee;
public class Streams {
	public static void main(String[] args) {
		Data data=new Data();
		List<Employee> employeeList=data.getEmployeeDetails();
		Queries queries=new Queries();
		System.out.println("Count of MAle and Female employees : "+queries.countNoOfMaleAndFemaleEmployees(employeeList));
		System.out.println();
		
		System.out.println("Department names are:"+queries.namesOfAllDepartments1(employeeList));
		System.out.println();
		
		System.out.println("Average age of male and female employeees : " +queries.averageAgeOfMaleAndFemale(employeeList));
		System.out.println();
		
		System.out.println("highest paid employee in the organization : "+queries.highestPaidEmployee(employeeList));
		System.out.println();
		
		System.out.println("names of all employees who have joined after 2015?"+queries.nameOfEmployeesAfter2015(employeeList));
		System.out.println();
	
		System.out.println("number of employees in each department : "+queries.NoOfEmployeeInEachDepartment(employeeList));
		System.out.println();
		
		System.out.println("average salary of each department : "+queries.averageSalaryOfEachDepartment(employeeList));
		System.out.println();
		
		System.out.println("details of youngest male employee in the product development department : "+queries.youngestMaleEmployee(employeeList));
		System.out.println();
		
		System.out.println("most working experience in the organization : "+queries.highestExperienceEmployee(employeeList));
		System.out.println();
		
		System.out.println("male and female employees are there in the sales and marketing team : "+queries.countMaleAndFemaleEmployeesInSalesDept(employeeList));
		System.out.println();
		
		System.out.println("the average salary of male and female employees : "+queries.maleFemaleAverageSalary(employeeList));
		System.out.println();
		
		System.out.println("names of all employees in each department : ");
		Map<Object, Set<Object>> employeeNamesInEachDepartment=queries.namesInEachDepartment(employeeList);
		System.out.println(employeeNamesInEachDepartment);
		System.out.println();
		
		System.out.println("Total salary is : "+queries.totalSalary(employeeList)+"  "+"Average salary is : "+queries.averageSalary(employeeList));	
		System.out.println();
		
		System.out.println("Employees <=25 are : "+queries.youngerEmployee(employeeList));
		System.out.println("Employees >25 are : "+queries.elderEmployee(employeeList));
		System.out.println();
		
		System.out.println("oldest employee in the organization : "+queries.oldestEmployee(employeeList));
		
		
	}

}
