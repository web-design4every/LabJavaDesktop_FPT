/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhap11;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author longd
 */
public class Controller {

    GUI gui = new GUI();
    ListCourse gi = new ListCourse();
    private String StockID;
    private String StockName;
    private String Address;
    private String date;
    private String note;
    ArrayList<source> list = new ArrayList<>();

    public Controller() {
        data();
        btnOpenItem();
        gui.setResizable(false);
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
    }

    public void btnOpenItem() {
        gui.getBtnop().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gui.setVisible(false);
                getValueTable();
                ControllerDialogDispay con = new ControllerDialogDispay(list);

            }
        });
    }

    public void data() {
        String[] columnName = {"StockID", "StockName", "Address", "DateAvailable", "Note"};
        // create data in table
        String[][] data = {
            {"1", "Stock one", "No1 - Washington street", "11/05/2010", ""},
            {"2", "Stock two", "372 Cave town - 001 Banks", "09/07/2011", ""},
            {"3", "Stock three", "Nary angel - 890 Number one", "13/05/2010", "Store dangerous materials"},
            {"4", "Stock four", "Twin tower - 01 Main street", "04/07/2015", ""},
            {"5", "Stock five", "Victory anniversary", "08/12/2014", ""}
        };

        if (checkTableRow()) {
            DefaultTableModel tableModel = new DefaultTableModel(data, columnName);
            gui.getTable().setModel(tableModel);
            gui.getTable().getColumnModel().getColumn(0).setPreferredWidth(10);
            gui.getTable().getColumnModel().getColumn(1).setPreferredWidth(50);
            gui.getTable().getColumnModel().getColumn(2).setPreferredWidth(150);
            gui.getTable().getColumnModel().getColumn(3).setPreferredWidth(60);
          gui.getTable().getColumnModel().getColumn(4).setPreferredWidth(150);
            gui.getTable().setRowSelectionInterval(2, 2);

        }

    }

    public boolean checkTableRow() {
        JTable table = gui.getTable();
        // count the number of row in table 
        int tableRow = table.getRowCount();
        if (tableRow > 0) {
            return true;
        }
        return false;
    }

    public void getValueTable() {
        StockID = gui.getTable().getValueAt(gui.getTable().getSelectedRow(), 0).toString();
        int id = Integer.parseInt(StockID);
        StockName = gui.getTable().getValueAt(gui.getTable().getSelectedRow(), 1).toString();
        Address = gui.getTable().getValueAt(gui.getTable().getSelectedRow(), 2).toString();
        date = gui.getTable().getValueAt(gui.getTable().getSelectedRow(), 3).toString();
        note = gui.getTable().getValueAt(gui.getTable().getSelectedRow(), 4).toString();
        list.add(new source(id, StockName, Address, date, note));
    }

}
