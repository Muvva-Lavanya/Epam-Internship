package com.epam;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	String employeeName;
	int employeeId;
	int employeeSalary;
	Address a1;

	public Employee(String employeeName, int employeeId, int employeeSalary, Address a1) {
		super();
		this.employeeName = employeeName;
		this.employeeId = employeeId;
		this.employeeSalary = employeeSalary;
		this.a1 = a1;
	}

	public void EmployeeDetails() {
		System.out.println(employeeName + " " + employeeId + " " + employeeSalary + " " + a1.floorNumber + " "
				+ a1.streetName + " " + a1.cityName + " " + a1.state + " " + a1.country);
	}
}

class JuniorEngineer extends Employee {
	int assesmentScore;
	String feedback;

	public JuniorEngineer(String employeeName, int employeeId, int employeeSalary, Address a1, int assesmentScore,
			String feedback) {
		super(employeeName, employeeId, employeeSalary, a1);
		this.assesmentScore = assesmentScore;
		this.feedback = feedback;
	}

	@Override
	public void EmployeeDetails() {
		super.EmployeeDetails();
		System.out.println("Assesment and feedback are " + assesmentScore + " " + feedback);
	}

}

class SoftwareEngineer extends Employee {
	String projectName;

	public SoftwareEngineer(String employeeName, int employeeId, int employeeSalary, Address a1, String projectName) {
		super(employeeName, employeeId, employeeSalary, a1);
		this.projectName = projectName;
	}

	@Override
	public void EmployeeDetails() {
		super.EmployeeDetails();
		System.out.println("Project name is " + projectName);
	}
}

class Trainer extends Employee {
	List<String> skills = new ArrayList<String>();
	List<String> certifications = new ArrayList<String>();

	public Trainer(String employeeName, int employeeId, int employeeSalary, Address a1, List<String> skills,
			List<String> certifications) {
		super(employeeName, employeeId, employeeSalary, a1);
		this.skills = skills;
		this.certifications = certifications;
	}

	@Override
	public void EmployeeDetails() {
		super.EmployeeDetails();
		System.out.println("Skills and Certifications are " + skills + " " + certifications);
	}

}