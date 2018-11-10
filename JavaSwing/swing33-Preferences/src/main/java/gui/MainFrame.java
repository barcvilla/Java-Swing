/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.PersonController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
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
    private PrefsDialog prefsDialog;
    private Preferences prefs;
    
    private PersonController personController;
    private TablePanel tablePanel;

    public MainFrame() {
        super("Hello World");
        
        setLayout(new BorderLayout());
        
        toolBar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();
        prefsDialog = new PrefsDialog(this);
        
        prefs = Preferences.userRoot().node("db");
        
        personController = new PersonController();
        tablePanel = new TablePanel();
        tablePanel.setData(personController.getPeople());
        
        tablePanel.setPersonTableListener(new PersonTableListener(){
            @Override
            public void rowDeleted(int row)
            {
                personController.removePerson(row);
            }
        });
        
        //se hizo click en el boton ok en PrefsDialog
        prefsDialog.setPrefsListener(new PrefsListener() {
            @Override
            public void preferenceSet(String user, String password, int port) {
                //System.out.println("User: " + user + " Password: " + password + " Puerto: " + port);
                prefs.put("user", user);
                prefs.put("password", password);
                prefs.putInt("port", port);
            }
        });
        
        String user = prefs.get("user", "");
        String password = prefs.get("password", "");
        Integer port = prefs.getInt("port", 3306);
        
        prefsDialog.setDefaults(user, password, port);
        
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
                personController.addPerson(e);
                tablePanel.refresh();
            }
        });
        
        add(formPanel, BorderLayout.WEST); // a la izquierda
        add(toolBar, BorderLayout.NORTH); // arriba
        add(tablePanel, BorderLayout.CENTER); // en medio
        
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
        JMenuItem prefsItem = new JMenuItem("preferences...");
        
        //SubMenu
        JMenu showMenu = new JMenu("Show");
        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person form");
        showFormItem.setSelected(true);
        showMenu.add(showFormItem);
        
        windownMenu.add(showMenu);
        windownMenu.add(prefsItem);
        
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
        
        prefsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prefsDialog.setVisible(true);
            }
        });
        
        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);
        
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        
        importDataItem.addActionListener((ActionEvent e) -> {
            if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
            {
                try 
                {
                    personController.loadFromFile(fileChooser.getSelectedFile());
                    tablePanel.refresh();
                }
                catch (IOException ex) 
                {
                    JOptionPane.showMessageDialog(MainFrame.this, "Could not load data from file", "Error", JOptionPane.ERROR_MESSAGE);
                    //Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        exportDataItem.addActionListener((ActionEvent e) -> {
            if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
            {
                try 
                {
                    personController.saveToFile(fileChooser.getSelectedFile());
                }
                catch (IOException ex) 
                {
                    JOptionPane.showMessageDialog(MainFrame.this, "Could not save data to file", "Error", JOptionPane.ERROR_MESSAGE);
                    //Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
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
