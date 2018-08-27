/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author PC
 */
public class FormPanel extends JPanel{
    
    public FormPanel()
    {
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        
        //creamos border
        Border innerBorder = BorderFactory.createTitledBorder("Add Persona");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }
}
