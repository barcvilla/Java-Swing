/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

/**
 *
 * @author PC
 */
public class ServerTreeCellRenderer implements TreeCellRenderer{
    
    private JCheckBox leafRenderer;
    private DefaultTreeCellRenderer noLeafRenderer;
    
    private Color textForeground;
    private Color textBackground;
    private Color selectionForeground;
    private Color selectionBackground;
    
    public ServerTreeCellRenderer()
    {
        leafRenderer = new JCheckBox();
        noLeafRenderer = new DefaultTreeCellRenderer();
        
        noLeafRenderer.setLeafIcon(Utils.createIcon("/image/Server16.gif"));
        noLeafRenderer.setOpenIcon(Utils.createIcon("/image/WebComponent16.gif"));
        noLeafRenderer.setClosedIcon(Utils.createIcon("/image/WebComponentAdd16.gif"));
        
        textForeground = UIManager.getColor("Tree.textForeground");
        textBackground = UIManager.getColor("Tree.textBackground");
        selectionForeground = UIManager.getColor("Tree.selectionForeground");
        selectionBackground = UIManager.getColor("Tree.selectionBackground");
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, 
            boolean leaf, int row, boolean hasFocus) {
        if(leaf)
        {
            if(selected)
            {
                leafRenderer.setForeground(selectionForeground);
                leafRenderer.setBackground(selectionBackground);
            }
            else
            {
                leafRenderer.setForeground(textForeground);
                leafRenderer.setBackground(textBackground);
            }
            
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
            ServerInfo nodeInfo = (ServerInfo)node.getUserObject();
            leafRenderer.setText(node.toString());
            leafRenderer.setSelected(nodeInfo.isChecked());
            return leafRenderer;
        }
        else
        {
            return noLeafRenderer.getTreeCellRendererComponent(tree, value, leaf, expanded, leaf, row, hasFocus);
        }
    }
    
}
