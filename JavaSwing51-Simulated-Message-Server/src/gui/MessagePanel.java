/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.MessageServer;
import java.awt.BorderLayout;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import model.Message;

class ServerInfo
{
    private String name;
    private int id;
    private boolean checked;
    
    public ServerInfo(String name, int id, boolean checked)
    {
        this.name = name;
        this.id = id;
        this.checked = checked;
    }
    
    public int getId()
    {
        return id;
    }
    
    public String toString()
    {
        return name;
    }
    
    public boolean isChecked()
    {
        return checked;
    }
    
    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }
}

public class MessagePanel extends JPanel{
    
    private JTree serverTree;
    private ServerTreeCellRenderer treeCellRenderer;
    private ServerTreeCellEditor treeCellEditor;
    
    private Set<Integer> selectedServers;
    private MessageServer messageServer;
    
    public MessagePanel()
    {
        messageServer = new MessageServer();
        selectedServers = new TreeSet<Integer>();
        selectedServers.add(0);
        selectedServers.add(1);
        selectedServers.add(4);
        
        serverTree = new JTree(createTree());
        
        treeCellRenderer = new ServerTreeCellRenderer();
        treeCellEditor = new ServerTreeCellEditor();
        
        serverTree.setCellRenderer(treeCellRenderer);
        serverTree.setCellEditor(treeCellEditor);
        serverTree.setEditable(true);
        //set events
        serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        /**
        serverTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                //obtenemos el nodo
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)serverTree.getLastSelectedPathComponent();
                //obtenemos el nombre
                Object userObject = node.getUserObject();
                if(userObject instanceof ServerInfo)
                {
                    int id = ((ServerInfo) userObject).getId();
                    System.out.println("User object with id: " + id);
                }
            }
        });
        **/
        
        treeCellEditor.addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                ServerInfo info = (ServerInfo)treeCellEditor.getCellEditorValue();
                System.out.println(info + ": " + info.getId() + "; " + info.isChecked());
                int serverId = info.getId();
                if(info.isChecked())
                {
                    selectedServers.add(serverId);
                }
                else
                {
                    selectedServers.remove(serverId);
                }
                
                messageServer.setSelectedServers(selectedServers);
                System.out.println("Message waiting: " + messageServer.getMessageCount());
                
                for(Message message : messageServer)
                {
                    System.out.println(message.getTitle());
                }
            }

            @Override
            public void editingCanceled(ChangeEvent e) {
                
            }
        });
        
        setLayout(new BorderLayout());
        add(new JScrollPane(serverTree), BorderLayout.CENTER);
    }
    
    private DefaultMutableTreeNode createTree()
    {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Servers");
        // creamos la jerarquia
        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("USA");
        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(new ServerInfo("New York", 0, selectedServers.contains(0)));
        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(new ServerInfo("Boston", 1, selectedServers.contains(1)));
        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(new ServerInfo("Los Angeles", 2, selectedServers.contains(2)));
        
        branch1.add(server1);
        branch1.add(server2);
        branch1.add(server3);
        
        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(new ServerInfo("London",3, selectedServers.contains(3)));
        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(new ServerInfo("Edinburgh",4, selectedServers.contains(4)));
        
        branch2.add(server4);
        branch2.add(server5);
        
        top.add(branch1);
        top.add(branch2);
        
        return top;
    }
}
