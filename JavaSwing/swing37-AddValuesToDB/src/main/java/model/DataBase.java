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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class DataBase {
    //facilita la eliminacion de elementos en el medio de la lista
    private List<Person> people;
    
    private Connection con;
    
    public DataBase()
    {
        people = new LinkedList<Person>();
    }
    
    public void removePerson(int index)
    {
        people.remove(index);
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
    
    public void connect() throws Exception
    {
        if(con != null) return;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String connectionStringUrl = "jdbc:mysql://localhost:3306/swingtest?useSSL=false";
            con = DriverManager.getConnection(connectionStringUrl, "root", "admin");
        }
        catch(ClassNotFoundException ex)
        {
            throw new Exception("Driver not found");
        }
    }
    
    public void disconnect()
    {
        if(con != null)
        {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void save() throws SQLException
    {
        String sql = "select count(*) as count from people where id = ?";
        PreparedStatement checkStmt = con.prepareStatement(sql);
        String insertSql = "insert into people "
                + "(id, "
                + "name, "
                + "age, "
                + "employment_status, "
                + "taxid, "
                + "us_citizen, "
                + "gender, "
                + "occupation) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?)"; 
        PreparedStatement insertStatement = con.prepareStatement(insertSql);
        
        String updateSql = "update  people "
                + "set name = ?, "
                + "age = ?, "
                + "employment_status = ?, "
                + "taxid = ?, "
                + "us_citizen = ?, "
                + "gender = ?, "
                + "occupation = ? where id = ?";
        PreparedStatement updateStatement = con.prepareStatement(updateSql);
        
        for(Person person : people)
        {
            int id = person.getId();
            String name = person.getName();
            AgeCategory age = person.getAgeCategory();
            EmploymentCategory empCat = person.getEmpCat();
            String tax = person.getTaxId();
            boolean isUs =  person.isUsCitizen();
            Gender gender = person.getGender();
            String occupation = person.getOccupation();
            
            checkStmt.setInt(1, id);
            ResultSet checkResult = checkStmt.executeQuery();
            checkResult.next();
            int count = checkResult.getInt(1);
            
            if(count == 0)
            {
                System.out.println("Inserting new Person with ID: " + id);
                
                insertStatement.setInt(1, id);
                insertStatement.setString(2, name);
                insertStatement.setString(3, age.name());
                insertStatement.setString(4, empCat.name());
                insertStatement.setString(5, tax);
                insertStatement.setBoolean(6, isUs);
                insertStatement.setString(7, gender.name());
                insertStatement.setString(8, occupation);
                
                insertStatement.executeUpdate();
            }
            else
            {
                System.out.println("Updating new Person with ID: " + id);
                
                updateStatement.setString(1, name);
                updateStatement.setString(2, age.name());
                updateStatement.setString(3, empCat.name());
                updateStatement.setString(4, tax);
                updateStatement.setBoolean(5, isUs);
                updateStatement.setString(6, gender.name());
                updateStatement.setString(7, occupation);
                updateStatement.setInt(8, id);
                
                updateStatement.executeUpdate();
            }
        }
        
        updateStatement.close();
        insertStatement.close();
        checkStmt.close();
    }
    
    public void addPerson(Person person)
    {
        people.add(person);
    }
    
    public List<Person> getPeople()
    {
        return Collections.unmodifiableList(people);
    }
}
