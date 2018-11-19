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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.IOException;
import java.sql.SQLException;
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
import javax.swing.JSplitPane;
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
    
    private JSplitPane splitPane;

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
        
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, tablePanel);
        splitPane.setOneTouchExpandable(true);

        tablePanel.setPersonTableListener(new PersonTableListener() {
            @Override
            public void rowDeleted(int row) {
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

        toolBar.setToolbarListener(new ToolBarListener() {
            @Override
            public void saveEventOccured() {
                connectToDataBase();

                try {
                    personController.save();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Unable to save to DataBase", "Save operation Problem", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }

            @Override
            public void refreshEventOccured() {
                connectToDataBase();

                try {
                    personController.load();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Unable to load data from DataBase", "Load operation Problem", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
                
                tablePanel.refresh();
            }

        });

        formPanel.setFormListener(new FormListener() {
            public void formEventOccurred(FormEvent e) {
                personController.addPerson(e);
                tablePanel.refresh();
            }
        });
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                personController.disconnect();
                dispose();
                System.gc();
            }
            
        });

        //add(formPanel, BorderLayout.WEST); // a la izquierda
        add(toolBar, BorderLayout.NORTH); // arriba
        add(splitPane, BorderLayout.CENTER);
        //add(tablePanel, BorderLayout.CENTER); // en medio

        setMinimumSize(new Dimension(500, 400));
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    private void connectToDataBase() {
        try {
            personController.connect();
        } catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(MainFrame.this, "Unable to connect to DataBase", "Database connection Problem", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JMenuBar createMenuBar() {
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
                if(menuItem.isSelected())
                {
                    splitPane.setDividerLocation((int)formPanel.getMinimumSize().getWidth());
                }
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

        prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        importDataItem.addActionListener((ActionEvent e) -> {
            if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                try {
                    personController.loadFromFile(fileChooser.getSelectedFile());
                    tablePanel.refresh();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Could not load data from file", "Error", JOptionPane.ERROR_MESSAGE);
                    //Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        exportDataItem.addActionListener((ActionEvent e) -> {
            if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                try {
                    personController.saveToFile(fileChooser.getSelectedFile());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Could not save data to file", "Error", JOptionPane.ERROR_MESSAGE);
                    //Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to exit the app?", "Confirm exit", JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    WindowListener[] listeners = getWindowListeners();
                    for(WindowListener listener : listeners)
                    {
                        listener.windowClosing(new WindowEvent(MainFrame.this, 0));
                    }
                }
            }
        });

        return menuBar;
    }
}
