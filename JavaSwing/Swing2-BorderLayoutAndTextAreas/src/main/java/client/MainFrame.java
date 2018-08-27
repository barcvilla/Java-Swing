/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.BorderLayout;
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
        
        add(textArea, BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);
        
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });

    }
}
