/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author PC
 */
public class FormPanel extends JPanel{
    
    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;
    
    
    public FormPanel()
    {
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        
        nameLabel = new JLabel("Name: ");
        occupationLabel = new JLabel("Occupation: ");
        nameField = new JTextField(10); //number characters accepted
        occupationField = new JTextField(10);
        
        okBtn = new JButton("Ok");
        
        //creamos border
        Border innerBorder = BorderFactory.createTitledBorder("Add Persona");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }
}
