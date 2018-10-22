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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
        
        setJMenuBar(createMenuBar());
        
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
                int ageCat = e.getAgeCategory();
                String empCat = e.getEmployeeCategory();
                boolean usCitizen = e.isUsCitizen();
                String taxId = e.getTaxId();
                String gender = e.getGender();
                
                textPanel.appendText(ageCat + ": " + name + ": " + occupation + ": " + empCat + ": " + usCitizen + ": " + taxId + ": " + gender + "\n");
            }
        });
        
        add(formPanel, BorderLayout.WEST); // a la izquierda
        add(toolBar, BorderLayout.NORTH); // arriba
        add(textPanel, BorderLayout.CENTER); // en medio
        
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private JMenuBar createMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        JMenuItem exportDataItem = new JMenuItem("Export Data...");
        JMenuItem importDataItem = new JMenuItem("Import Data...");
        JMenuItem exit = new JMenuItem("Exit");
        
        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exit);
        
        JMenu windownMenu = new JMenu("Window");
        
        //SubMenu
        JMenu showMenu = new JMenu("Show");
        JMenuItem showFormItem = new JMenuItem("Person form");
        showMenu.add(showFormItem);
        
        windownMenu.add(showMenu);
        
        menuBar.add(fileMenu);
        menuBar.add(windownMenu);
        return menuBar;
    }
}
