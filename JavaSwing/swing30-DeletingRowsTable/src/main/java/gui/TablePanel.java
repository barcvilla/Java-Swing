/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.Person;

/**
 *
 * @author PC
 */
public class TablePanel extends JPanel {
    private JTable table;
    private PersonTableModel tableModel;
    private JPopupMenu popup;
    private PersonTableListener personTableListener;
    
    public TablePanel()
    {
        tableModel = new PersonTableModel();
        table = new JTable(tableModel);

        popup = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("Delete row");
        popup.add(removeItem);
        
        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint()); //obtenemos la fila de la tabla
                table.getSelectionModel().setSelectionInterval(row, row); //seleccionamos toda la fila
                if(e.getButton() == MouseEvent.BUTTON3)
                {
                    popup.show(table, e.getX(), e.getY());
                }
            }
            
        });
        
        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if(personTableListener != null)
                {
                    personTableListener.rowDeleted(row);
                    tableModel.fireTableRowsDeleted(row, row);
                }
            }
        });
        
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
    
    public void setPersonTableListener(PersonTableListener listener)
    {
        this.personTableListener = listener;
    }
    
    public void setData(List<Person> people)
    {
        tableModel.setData(people);
    }
    
    public void refresh()
    {
        tableModel.fireTableDataChanged();
    }
}
