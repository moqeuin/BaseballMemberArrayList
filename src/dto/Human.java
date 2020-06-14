package dto;

public class Human {

	private int number;	//선수의 등록번호
	private String name;//선수의 이름
	private int age;//선수의 나이
	private double height;//선수의 신장
	
	public Human() {
	}

	public Human(int number, String name, int age, double height) {
		this.number = number;
		this.name = name;
		this.age = age;
		this.height = height;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return number + "-" + name + "-" + age + "-" + height;
	}
}