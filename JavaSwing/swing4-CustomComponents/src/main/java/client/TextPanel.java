/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author PC
 */
public class TextPanel extends JPanel{
    private JTextArea textArea;
    
    public TextPanel()
    {
        textArea = new JTextArea();
        
        setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }
    
    public void appendText(String message)
    {
        textArea.append(message);
    }
}
