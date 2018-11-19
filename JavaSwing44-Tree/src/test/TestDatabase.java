/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AgeCategory;
import model.DataBase;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

/**
 *
 * @author PC
 */
public class TestDatabase {
    public static void main(String[] args) {
        System.out.println("Running database test");
        DataBase db = new DataBase();
        try {
            db.connect();
        } catch (Exception ex) {
            Logger.getLogger(TestDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        db.addPerson(new Person("John", "Software Analyst", AgeCategory.adult, EmploymentCategory.employed, true, "777", Gender.male));
        db.addPerson(new Person("Paul", "IT Manager", AgeCategory.adult, EmploymentCategory.employed, true, null, Gender.male));
        
        try
        {
            db.save();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        try {
            db.getAllPersons();
        } catch (SQLException ex) {
            Logger.getLogger(TestDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        db.disconnect();
    }
}
