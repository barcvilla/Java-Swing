package controller;

import gui.FormEvent;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

public class Controller {
	Database db = new Database();
	
	public List<Person> getPeople() {
		return db.getPeople();
	}
	
	public void removePerson(int index) {
		db.removePerson(index);
	}
	
	public void save() throws SQLException {
		db.save();
	}
	
	public void load() throws SQLException {
		db.load();
	}
	
	public void configure(int port, String user, String password) throws Exception {
		db.configure(port, user, password);
	}
	
	public void connect() throws Exception {
		db.connect();
	}
	
	public void disconnect() {
		db.disconnect();
	}
	
	public void addPerson(FormEvent ev) {
		String name = ev.getName();
		String occupation = ev.getOccupation();
		int ageCatId = ev.getAgeCategory();
		String empCat = ev.getEmploymentCategory();
		boolean isUs = ev.isUsCitizen();
		String taxId = ev.getTaxId();
		String gender = ev.getGender();
		
		AgeCategory ageCategory = null;
		
		switch(ageCatId) {
		case 0:
			ageCategory = AgeCategory.child;
			break;
		case 1:
			ageCategory = AgeCategory.adult;
			break;
		case 2:
			ageCategory = AgeCategory.senior;
			break;
		}
		
		EmploymentCategory empCategory;
		
		if(empCat.equals("employed")) {
			empCategory = EmploymentCategory.employed;
		}
		else if(empCat.equals("self-employed")) {
			empCategory = EmploymentCategory.selfEmployed;
		}
		else if(empCat.equals("unemployed")) {
			empCategory = EmploymentCategory.unemployed;
		}
		else {
			empCategory = EmploymentCategory.other;
			System.err.println(empCat);
		}
		
		Gender genderCat;
		
		if(gender.equals("male")) {
			genderCat = Gender.male;
		}
		else {
			genderCat = Gender.female;
		}
		
		Person person = new Person(name, occupation, ageCategory, empCategory, 
				taxId, isUs, genderCat);
		
		db.addPerson(person);
	}
	
	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}
}
