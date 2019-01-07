/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.SQLException;
import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

/* 
 * You must fill in the correct user name and password to use this class.
 */

public class TestDatabase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Running database test");

		Database db = new Database();
		try {
			db.configure(3306, "someone", "password");
			db.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.addPerson(new Person("Joe", "lion tamer", AgeCategory.adult, EmploymentCategory.employed, "777", true, Gender.male));
		db.addPerson(new Person("Sue", "artist", AgeCategory.adult, EmploymentCategory.selfEmployed, null, false, Gender.female));
		
		try {
			db.save();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			db.load();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.disconnect();
	}

}
