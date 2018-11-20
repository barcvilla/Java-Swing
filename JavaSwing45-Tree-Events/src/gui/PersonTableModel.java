/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Person;

/**
 *
 * @author PC
 */
public class PersonTableModel extends AbstractTableModel{
    
    private List<Person> db;
    private String[] colNames = {"Id", "Name", "Occupation", "Age Category", "Employement Category", "US Citizen", "Tax ID"};
    
    public PersonTableModel()
    {
        
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    
    
    public void setData(List<Person> people)
    {
        this.db = people;
    }

    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Person person = db.get(rowIndex);
        switch(columnIndex)
        {
            case 0:
                return person.getId();
            case 1:
                return person.getName();
            case 2:
                return person.getOccupation();
            case 3:
                return person.getAgeCategory();
            case 4:
                return person.getEmpCat();
            case 5:
                return person.isUsCitizen();
            case 6:
                return person.getTaxId();
        }
        return null;
    }
    
}
