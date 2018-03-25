package pl.coderslab.meowczki.JEE_Exam_5.controllers;

public class Animal {
	public Animal(String name, String type) {

		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String name;
	private String type;
}
