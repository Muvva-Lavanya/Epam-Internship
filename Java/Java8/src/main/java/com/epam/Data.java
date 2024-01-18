package com.epam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Data {

	public List<Product> getProducts() {
		Product product1 = new Product("Electronics", "Iphone", 1500, "high-end");
        Product product2 = new Product("Electronics", "MacBook", 10000, "high-end");
        Product product3 = new Product("Electronics", "watch", 45, "economy");

        Product product4 = new Product("Women's Fashion", "Fast-Track Watch", 87, "economy");
        Product product5 = new Product("Women's Fashion", "Leather HandBag", 200, "value");

        Product product6 = new Product("Men's Fashion", "T-Shirt", 50, "standard");
        Product product7 = new Product("Men's Fashion", "Reebok Shoes", 57, "value");
        Product product8 = new Product("Men's Fashion", "Titan Watch", 659, "high-end");

        Product product9 = new Product("Kids", "HeadBands", 330, "value");
        Product product10 = new Product("Kids", "Canvas Cap", 213, "budget");
        Product product11 = new Product("Kids", "Baby SunGlasses", 425, "standard");
        Product product12 = new Product("Kids", "iLearn Baby Toys", 765, "high-end");
        Product product13 = new Product("Kids", "Montessori Toys", 89, "budget");

        Product product14 = new Product("Books", "Clean Code Principles", 1000, "high-end");
        Product product15 = new Product("Books", "Microservices", 230, "budget");
        Product product16 = new Product("Books", "Design Patterns & Principles", 450, "standard");
        Product product17 = new Product("Kids", "Indoor Playground Set", 324, "economy");

		return Arrays.asList(product1, product2, product3, product4, product5, product6, product7, product8, product9,
				product10, product11, product12, product13, product14, product15, product16, product17);

	}
	public List<Employee> getEmployeeDetails()
	{
		List<Employee> employeeList = new ArrayList<Employee>();
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
		return employeeList;

	}
	public List<Integer> getNumbers()
	{
		Random random=new Random();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for (int counter = 0; counter < 20; counter++) {
			numbers.add(random.nextInt(0,1000));
		}
		return numbers;
	}

}
