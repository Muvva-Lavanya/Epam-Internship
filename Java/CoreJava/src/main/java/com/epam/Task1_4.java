package com.epam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import com.epam.Address;
//import com.epam.SoftwareEngineer;
//import com.epam.JuniorEngineer;
//import com.epam.Employee;
//import com.epam.Trainer;
public class Task1_4 {
	public static void main(String args[]) {
		Address a = new Address(10, "vidhyanagar", "ponnur", "AP", "India");
		Employee e = new Employee("lavanya", 4214, 500000, a);
		JuniorEngineer j = new JuniorEngineer("lavanya", 4214, 50000, a, 9, "good");
		SoftwareEngineer s = new SoftwareEngineer("lavanya", 4214, 50000, a, "HeartDiseasePrediction");
		List<String> skills = Arrays.asList("c", "java", "python");
		List<String> certifications = Arrays.asList("python programming", "iot");
		Trainer t = new Trainer("lavanya", 4214, 50000, a, skills, certifications);
		System.out.print("EmployeeDetails are :");
		e.EmployeeDetails();
		System.out.println();
		System.out.print("JuniorEngineerDetails are :");
		s.EmployeeDetails();
		System.out.println();
		System.out.print("SoftwareEngineerDetails are :");
		j.EmployeeDetails();
		System.out.println();
		System.out.print("TrainerDetails are :");
		t.EmployeeDetails();
		System.out.println();

	}
}