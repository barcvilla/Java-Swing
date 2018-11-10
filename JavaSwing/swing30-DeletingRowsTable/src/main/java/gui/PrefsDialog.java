/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author PC
 */
public class PrefsDialog extends JDialog{
    public PrefsDialog(JFrame parent)
    {
        super(parent, "Preferences", false);
        setSize(400, 300);
    }
}
