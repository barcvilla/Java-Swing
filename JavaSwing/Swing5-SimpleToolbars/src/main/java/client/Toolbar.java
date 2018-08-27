/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class Toolbar extends JPanel{
    
    private JButton helloButton;
    private JButton byeButton;
    
    public Toolbar()
    {
        helloButton = new JButton("Hello");
        byeButton = new JButton("Good bye");
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        add(helloButton);
        add(byeButton);
    }
}
