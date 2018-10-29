/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author PC
 */
public class MainFrame extends JFrame {

    
    private TextPanel textPanel;
    private Toolbar toolBar;
    private FormPanel formPanel;
    private JFileChooser fileChooser;

    public MainFrame() {
        super("Hello World");
        
        setLayout(new BorderLayout());
        
        toolBar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();
        
        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter());
        
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
        
        setMinimumSize(new Dimension(500, 400));
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
        JMenuItem exitItem = new JMenuItem("Exit");
        
        fileMenu.add(importDataItem);
        fileMenu.add(exportDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        JMenu windownMenu = new JMenu("Window");
        
        //SubMenu
        JMenu showMenu = new JMenu("Show");
        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person form");
        showFormItem.setSelected(true);
        showMenu.add(showFormItem);
        
        windownMenu.add(showMenu);
        
        menuBar.add(fileMenu);
        menuBar.add(windownMenu);
        
        showFormItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // recibimos el evento que viene de activar el checkbox
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();
                formPanel.setVisible(menuItem.isSelected());
            }
        });
        
        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);
        
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        
        importDataItem.addActionListener((ActionEvent e) -> {
            if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
            {
                
            }
        });
        
        exportDataItem.addActionListener((ActionEvent e) -> {
            if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
            {
                
            }
        });
        
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to exit the app?", "Confirm exit", JOptionPane.OK_CANCEL_OPTION);
                if(action == JOptionPane.OK_OPTION)
                {
                    System.exit(0);
                }
            }
        });
        
        return menuBar;
    }
}
