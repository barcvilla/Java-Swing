/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC
 */
public class MainFrame extends JFrame {

    private JButton btn;
    private JTextArea textArea;

    public MainFrame() {
        super("Hello World");
        
        setLayout(new BorderLayout());
        
        textArea = new JTextArea();
        btn = new JButton("Click me!");
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("Hello\n");
            }
        });
        
        add(textArea, BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);
        
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
