package com.kosten;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.LinkedList;

public class TableModel extends AbstractTableModel {

    private LinkedList<String[]> dataLinkedList;


    public TableModel() {
        dataLinkedList = new LinkedList<>();
        for (int i = 0; i< dataLinkedList.size(); i++){
            dataLinkedList.add(new String[getColumnCount()]);
        }
    }

    @Override
    public int getRowCount() {
        return dataLinkedList.size();
    }

    @Override
    public int getColumnCount() {
        int columnCount = 4;
        return columnCount;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0: return "Имя";
            case 1: return "Масса";
            case 2: return "Х";
            case 3: return  "У";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] row = dataLinkedList.get(rowIndex);
        return row[columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String[] st =dataLinkedList.get(rowIndex);
        //System.out.println(Arrays.toString(st));
        st[columnIndex]=(String)aValue;
        dataLinkedList.set(rowIndex,st);
    }

    public void addData (String [] row){
        String [] rowTable = new String[getColumnCount()];
        rowTable=row;
        dataLinkedList.add(rowTable);
    }

}
