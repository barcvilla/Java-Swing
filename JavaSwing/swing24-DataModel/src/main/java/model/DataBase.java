/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class DataBase {
    private ArrayList<Person> people;
    
    public DataBase()
    {
        people = new ArrayList<Person>();
    }
    
    public void addPerson(Person person)
    {
        people.add(person);
    }
    
    public java.util.List<Person> getPeople()
    {
        return people;
    }
}
