/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author PC
 */
public class PrefsDialog extends JDialog{
    private JButton okButton;
    private JButton cancelButton;
    private JSpinner portSpinner;
    private SpinnerModel spinnerModel;
    private JTextField userField;
    private JPasswordField passwordField;
    private PrefsListener prefsListener;
    
    public PrefsDialog(JFrame parent)
    {
        super(parent, "Preferences", false);
        okButton = new JButton("Ok");
        cancelButton =  new JButton("Cancel");
        
        spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
        portSpinner = new JSpinner(spinnerModel);
        
        userField = new JTextField(10);
        passwordField = new JPasswordField(10);
        passwordField.setEchoChar('*');
        
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.gridy = 0;
        
        /// First Row ///
        
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        
        gc.gridx = 0;
        
        add(new JLabel("User: "), gc);
        
        gc.gridx++;
        add(userField, gc);
        
        /// Next Row ///
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        
        gc.gridx = 0;
        
        add(new JLabel("Password: "), gc);
        
        gc.gridx++;
        add(passwordField, gc);
        
        
        /// Next Row ///
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        
        gc.gridx = 0;
        
        add(new JLabel("Port: "), gc);
        
        gc.gridx++;
        add(portSpinner, gc);
        
        /// next row ///
        gc.gridy++;
        gc.gridx = 0;
        add(okButton, gc);
        
        gc.gridx++;
        add(cancelButton, gc);
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int port = Integer.parseInt(portSpinner.getValue().toString());
                String user = userField.getText();
                char[] password = passwordField.getPassword();
                //mostramos el user y pass
                //System.out.println("user: " + user + " pass: " + new String(password));
                if(prefsListener != null)
                {
                    prefsListener.preferenceSet(user, new String(password), port);
                }
                setVisible(false);
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        
        setSize(400, 300);
        setLocationRelativeTo(parent);
    }
    
    public void setDefaults(String user, String password, int port)
    {
        userField.setText(user);
        passwordField.setText(password);
        portSpinner.setValue(port);
    }
    
    public void setPrefsListener(PrefsListener prefsListener)
    {
        this.prefsListener = prefsListener;
    }
}
