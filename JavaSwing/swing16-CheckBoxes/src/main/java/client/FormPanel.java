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
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
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
    private JList ageList;
    private JComboBox empCombo;
    private JCheckBox citizenCheckBox;
    private JTextField taxField;
    private JLabel taxLabel;
    
    public FormPanel()
    {
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        
        nameLabel = new JLabel("Name: ");
        occupationLabel = new JLabel("Occupation: ");
        nameField = new JTextField(10); //number characters accepted
        occupationField = new JTextField(10);
        ageList = new JList();
        empCombo = new JComboBox();
        citizenCheckBox = new JCheckBox();
        taxLabel = new JLabel("Tax ID: ");
        taxField =  new JTextField(10);
        
        //Set Up Tax ID
        taxLabel.setEnabled(false);
        taxField.setEnabled(false);
        
        citizenCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isTicked = citizenCheckBox.isSelected();
                taxLabel.setEnabled(isTicked);
                taxField.setEnabled(isTicked);
            }
        });
        
        //Setting JList
        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new AgeCategory(1, "Under to 18"));
        ageModel.addElement(new AgeCategory(2, "18 to 65"));
        ageModel.addElement(new AgeCategory(3, "65 to over"));
        
        //indicamos que el JList agelist utilice el ageModel
        ageList.setModel(ageModel);
        
        //aplicamos propiedades al JList
        ageList.setPreferredSize(new Dimension(110, 66));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setSelectedIndex(1);
        
        //Setting JComboBox
        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        empModel.addElement("Employeed");
        empModel.addElement("Self-Employeed");
        empModel.addElement("Un-Employeed");
        
        empCombo.setModel(empModel);
        empCombo.setSelectedIndex(0);
        
        
        okBtn = new JButton("Ok");
        
        okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String occupation = occupationField.getText();
                AgeCategory ageCat = (AgeCategory)ageList.getSelectedValue();
                String empCat = (String)empCombo.getSelectedItem();
                boolean usCitizen = citizenCheckBox.isSelected();
                String taxId = taxField.getText();
                
                
                System.out.println(empCat);
                
                //pasamos valores al FormEvent
                FormEvent ev = new FormEvent(this, name, occupation, ageCat.getId(), empCat, usCitizen, taxId);
                
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
        
        layoutComponents();
    }
    
    public void layoutComponents()
    {
        setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();        
        
        /*Primera linea*/
        gc.gridy = 0; // fila
        
        
        gc.weightx = 1;
        gc.weighty = 0.1;
        
        gc.gridx = 0; //columna
        gc.insets = new Insets(0, 0, 0, 5);
        gc.fill =  GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END; //colocamos los label al final de la fila
        add(nameLabel, gc);
        
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START; // colocamos el textfield al inicio de la fila
        add(nameField, gc);
        
        /*Siguiente linea*/
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty = 0.1;
        
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(occupationLabel, gc);
        
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(occupationField, gc);
        
        /*Siguiente linea*/
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty = 0.1; //tamanio del ancho de linea
        
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Age: "), gc);
        
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(ageList, gc);
        
        /*Siguiente linea*/
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty = 0.1; //tamanio del ancho de linea
        
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Employment: "), gc);
        
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(empCombo, gc);
        
        /*Siguiente linea*/
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty = 0.1; //tamanio del ancho de linea
        
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("US citizen: "), gc);
        
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(citizenCheckBox, gc);
        
        /*Siguiente linea*/
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty = 0.1; //tamanio del ancho de linea
        
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(taxLabel, gc);
        
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(taxField, gc);
        
        /*Siguiente linea*/
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty = 2.0;
        
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

class AgeCategory
{
    private int id;
    private String text;
    
    public AgeCategory(int id, String text)
    {
        this.id = id;
        this.text = text;
    }
    
    public String toString()
    {
        return text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
}
