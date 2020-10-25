package sample;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JavaLambdasExample1 {

	public static void main(String[] args) {
		
		List<Person> people = Arrays.asList(
				new Person("Bob", "John", 25),
				new Person("Rob", "Fred", 50),
				new Person("Frank", "Taylor", 60),
				new Person("David", "Simons", 58)
		);
		
		//sort by first name usinf lambdas
		Collections.sort(people, (p1,p2) -> p1.getfName().compareTo(p2.getfName()));
		// print list
		people.forEach(x -> System.out.println(x));

	}

}

class Person {
	String fName;
	String lName;
	int age;

	public Person(String fName, String lName, int age) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.age = age;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String toString() {
		return "Fname:"+ this.fName + ":LName:" + this.lName + ":age:" + this.age;
	}
}
