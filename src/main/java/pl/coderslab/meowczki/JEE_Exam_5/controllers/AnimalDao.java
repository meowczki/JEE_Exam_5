package pl.coderslab.meowczki.JEE_Exam_5.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class AnimalDao {
	private  List<Animal> animals = new ArrayList<>();

	public  List<Animal> getList() {
		animals.add(new Animal("kot", "ssak"));
		animals.add(new Animal("pies", "ssak"));
		animals.add(new Animal("papuga", "ptak"));
		animals.add(new Animal("rekin", "ryba"));


		return animals;

	}
}
