/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Window;
import javax.swing.JDialog;

/**
 *
 * @author PC
 */
public class ProgressDialog extends JDialog {

    public ProgressDialog(Window parent) {
        super(parent, "Messages Downloading...", ModalityType.APPLICATION_MODAL);
        setSize(400, 200);
        setLocationRelativeTo(parent);
    }

    
}
