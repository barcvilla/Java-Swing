/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.EventObject;

/**
 *
 * @author PC
 */
public class FormEvent extends EventObject {
    private String name;
    private String occupation;
    private int ageCategory;
    private String empCat;
    
    public FormEvent(Object source) {
        super(source);
    }
    
    public FormEvent(Object source, String name, String occupation, int ageCat, String empCat) {
        super(source);
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCat;
        this.empCat = empCat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(int ageCategory) {
        this.ageCategory = ageCategory;
    }

    public String getEmployeeCategory() {
        return empCat;
    }

    public void setEmployeeCategory(String empCat) {
        this.empCat = empCat;
    }
    
    
}
