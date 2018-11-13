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

/**
 *
 * @author PC
 */
public class Toolbar extends JPanel implements ActionListener {

    private JButton saveButton;
    private JButton refreshButton;

    private ToolBarListener textListener;

    public Toolbar() {
        setBorder(BorderFactory.createEtchedBorder());
        saveButton = new JButton("Save");
        saveButton.setIcon(createIcon("/image/Save16.gif"));
        
        refreshButton = new JButton("Refresh");
        refreshButton.setIcon(createIcon("/image/Refresh16.gif"));

        saveButton.addActionListener(this);
        refreshButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(saveButton);
        add(refreshButton);
    }
    
    private ImageIcon createIcon(String path)
    {
        URL url = getClass().getResource(path);
        if(url == null)
        {
            System.err.println("Unable to load image: " + path);
        }
        
        ImageIcon icon = new ImageIcon(url);
        return icon;
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
