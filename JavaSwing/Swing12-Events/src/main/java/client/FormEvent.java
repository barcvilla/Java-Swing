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
    
    public FormEvent(Object source) {
        super(source);
    }
    
    public FormEvent(Object source, String name, String occupation) {
        super(source);
        this.name = name;
        this.occupation = occupation;
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
    
    
}
