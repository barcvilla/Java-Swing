/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private FormListener formListener;
    
    
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
        
        okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String occupation = occupationField.getText();
                
                //pasamos valores al FormEvent
                FormEvent ev = new FormEvent(this, name, occupation);
                
                if(formListener != null)
                {
                    formListener.formEventOccurred(ev);
                }
            }
        });
        
        //creamos border
        Border innerBorder = BorderFactory.createTitledBorder("Add Persona");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();        
        
        /*Primera linea*/
        gc.weightx = 1;
        gc.weighty = 0.1;
        
        gc.gridx = 0; //columna
        gc.gridy = 0; // fila
        gc.insets = new Insets(0, 0, 0, 5);
        gc.fill =  GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END; //colocamos los label al final de la fila
        add(nameLabel, gc);
        
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START; // colocamos el textfield al inicio de la fila
        add(nameField, gc);
        
        /*Seguda linea*/
        gc.weightx = 1;
        gc.weighty = 0.1;
        
        gc.gridy = 1;
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(occupationLabel, gc);
        
        gc.gridy = 1;
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(occupationField, gc);
        
        /*Tercera linea*/
        gc.weightx = 1;
        gc.weighty = 2.0;
        
        gc.gridy = 2;
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(okBtn, gc);
    }
    
    public void setFormListener(FormListener listener)
    {
        this.formListener = listener;
    }
}
