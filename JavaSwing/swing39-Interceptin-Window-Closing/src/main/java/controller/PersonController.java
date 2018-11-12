/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.FormEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import model.AgeCategory;
import model.DataBase;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

/**
 *
 * @author PC
 */
public class PersonController {
    
    DataBase db =  new DataBase();
    
    public void connect() throws Exception
    {
        db.connect();
    }
    
    public void save() throws SQLException
    {
        db.save();
    }
    
    public void load() throws SQLException
    {
        db.getAllPersons();
    }
    
    public void disconnect()
    {
        db.disconnect();
    }
    
    public List<Person> getPeople()
    {
        return db.getPeople();
    }
    
    public void removePerson(int index)
    {
        db.removePerson(index);
    }
    
    public void saveToFile(File file) throws IOException
    {
        db.saveToFile(file);
    }
    
    public void loadFromFile(File file) throws IOException
    {
        db.loadFromFile(file);
    }
    
    public void addPerson(FormEvent e) {
        String name = e.getName();
        String occupation = e.getOccupation();
        int ageCategoryId = e.getAgeCategory();
        String empCat = e.getEmployeeCategory();
        boolean isUsCitizen = e.isUsCitizen();
        String taxId = e.getTaxId();
        String gender = e.getGender(); 
        
        AgeCategory ageCategory = null;
        switch(ageCategoryId)
        {
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
        
        EmploymentCategory employmentCategory = null;
        if(empCat.equals("Employed"))
        {
            employmentCategory = EmploymentCategory.employed;
        }
        else if(empCat.equals("Self-Employed"))
        {
            employmentCategory = EmploymentCategory.selfEmployed;
        }
        else if(empCat.equals("Un-Employed"))
        {
            employmentCategory = EmploymentCategory.unEmployed;
        }
        else
        {
            employmentCategory =  EmploymentCategory.other;
            System.err.println(empCat);
        }
        
        Gender genderType;
        if(gender.equals("Male"))
        {
            genderType = Gender.male;
        }
        else
        {
            genderType = Gender.female;
        }
        
        
        Person person = new Person(name, occupation, ageCategory, employmentCategory, isUsCitizen, taxId, genderType);
        db.addPerson(person);
    }
}
