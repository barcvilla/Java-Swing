/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class Toolbar extends JPanel implements ActionListener{
    
    private JButton helloButton;
    private JButton byeButton;
    private TextPanel textPanel;
    public Toolbar()
    {
        helloButton = new JButton("Hello");
        byeButton = new JButton("Good bye");
        
        helloButton.addActionListener(this);
        byeButton.addActionListener(this);
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        add(helloButton);
        add(byeButton);
    }
    
    public void setTextPanel(TextPanel textPanel)
    {
        this.textPanel = textPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if(clicked == helloButton)
        {
            textPanel.appendText("Hello\n");
        }
        else if(clicked == byeButton)
        {
            textPanel.appendText("Good bye\n");
        }
    }
}
