/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public void saveToFile(File file) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Person[] persons = people.toArray(new Person[people.size()]);
        oos.writeObject(persons);
        
        oos.close();
    }
    
    public void loadFromFile(File file) throws IOException
    {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        try 
        {
            Person[] persons = (Person[])ois.readObject();
            people.clear();
            people.addAll(Arrays.asList(persons));
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ois.close();
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
