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
public class Toolbar extends JPanel implements ActionListener {

    private JButton helloButton;
    private JButton byeButton;

    private StringListener textListener;

    public Toolbar() {
        helloButton = new JButton("Hello");
        byeButton = new JButton("Good bye");

        helloButton.addActionListener(this);
        byeButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(helloButton);
        add(byeButton);
    }

    public void setStringListener(StringListener listener) {
        this.textListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == helloButton) {
            if (textListener != null) {
                textListener.textEmitted("Hello\n");
            }
        } else if (clicked == byeButton) {
            if (textListener != null) {
                textListener.textEmitted("Goodbye\n");
            }

        }
    }
}
