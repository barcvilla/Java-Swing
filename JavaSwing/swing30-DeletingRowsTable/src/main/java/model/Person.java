/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class Person implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public static int count = 0;
    private int id;
    private String name;
    private String occupation;
    private AgeCategory ageCategory;
    private EmploymentCategory empCat;
    private boolean usCitizen;
    private String taxId;
    private Gender gender;

    public Person(String name, String occupation, AgeCategory ageCategory, EmploymentCategory empCat, boolean usCitizen, String taxId, Gender gender) {
        this.id = ++count;
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.empCat = empCat;
        this.usCitizen = usCitizen;
        this.taxId = taxId;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(AgeCategory ageCategory) {
        this.ageCategory = ageCategory;
    }

    public EmploymentCategory getEmpCat() {
        return empCat;
    }

    public void setEmpCat(EmploymentCategory empCat) {
        this.empCat = empCat;
    }

    public boolean isUsCitizen() {
        return usCitizen;
    }

    public void setUsCitizen(boolean usCitizen) {
        this.usCitizen = usCitizen;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", occupation=" + occupation + ", ageCategory=" + ageCategory + ", empCat=" + empCat + ", usCitizen=" + usCitizen + ", taxId=" + taxId + ", gender=" + gender + '}';
    }
    
    
}
