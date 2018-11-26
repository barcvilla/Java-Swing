/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author PC
 */
public class Toolbar extends JToolBar implements ActionListener {

    private JButton saveButton;
    private JButton refreshButton;

    private ToolBarListener textListener;
    
    //private static ImageIcon iSave = new ImageIcon(Toolbar.class.getResource("save16.gif"));
    //private static ImageIcon iRefresh = new ImageIcon(Toolbar.class.getResource("Refresh16.gif"));

    public Toolbar() {
        setBorder(BorderFactory.createEtchedBorder());
        saveButton = new JButton();
        saveButton.setIcon(Utils.createIcon("/image/save16.gif"));
        saveButton.setToolTipText("Save");
        
        refreshButton = new JButton();
        refreshButton.setIcon(Utils.createIcon("/image/refresh16.gif"));
        refreshButton.setToolTipText("Refresh");

        saveButton.addActionListener(this);
        refreshButton.addActionListener(this);

        add(saveButton);
        addSeparator();
        add(refreshButton);
    }
    
    

    public void setToolbarListener(ToolBarListener listener) {
        this.textListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == saveButton) {
            if (textListener != null) {
                textListener.saveEventOccured();
            }
        } else if (clicked == refreshButton) {
            if (textListener != null) {
                textListener.refreshEventOccured();
            }

        }
    }
}
