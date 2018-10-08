/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC
 */
public class MainFrame extends JFrame {

    
    private TextPanel textPanel;
    private Toolbar toolBar;
    private FormPanel formPanel;

    public MainFrame() {
        super("Hello World");
        
        setLayout(new BorderLayout());
        
        toolBar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();
        
        
        toolBar.setStringListener(new StringListener(){
            @Override
            public void textEmitted(String text) {
                textPanel.appendText(text);
            }
            
        });
        
        formPanel.setFormListener(new FormListener(){
            public void formEventOccurred(FormEvent e)
            {
                String name = e.getName();
                String occupation = e.getOccupation();
                
                textPanel.appendText(name + ": " + occupation + "\n");
            }
        });
        
        add(formPanel, BorderLayout.WEST); // a la izquierda
        add(toolBar, BorderLayout.NORTH); // arriba
        add(textPanel, BorderLayout.CENTER); // en medio
        
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
