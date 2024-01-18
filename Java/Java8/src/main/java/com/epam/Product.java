package com.epam;
public class Product
{
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	String Category;
	String name;
	int Price;
	String Grade;
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	public String getGrade() {
		return Grade;
	}
	public void setGrade(String grade) {
		Grade = grade;
	}
	public Product(String category, String name, int price, String grade) {
		super();
		Category = category;
		this.name = name;
		Price = price;
		Grade = grade;
	}
	@Override
	public String toString() {
		return "Product [Category=" + Category + ", name=" + name + ", Price=" + Price + ", Grade=" + Grade + "]";
	}
	
}