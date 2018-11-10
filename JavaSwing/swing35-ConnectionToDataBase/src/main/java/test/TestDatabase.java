/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.DataBase;

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
        db.disconnect();
    }
}
