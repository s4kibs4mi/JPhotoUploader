package com.sakibsami.jphotouploader;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Vector;

public class PhotoHolderModel extends AbstractTableModel {
    private static final String[] columnNames = new String[]{"Name", "Path", "Status", "Upload Link"};
    private static final Class[] columnClasses = new Class[]{String.class, String.class, String.class, String.class};

    private ArrayList<Vector> photoList = new ArrayList<>();

    @Override
    public int getRowCount() {
        return photoList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class getColumnClass(int col) {
        return columnClasses[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            return photoList.get(rowIndex).get(columnIndex);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void setValueAt(Object o, int row, int col) {
        try {
            photoList.get(row).set(col, o);
            fireTableDataChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addItem(Vector item) {
        photoList.add(item);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void removeItem(int row) {
        photoList.remove(row);
    }

    public void removeAll() {
        photoList.clear();
        fireTableDataChanged();
    }
}