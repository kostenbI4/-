package com.kosten;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Opener {
    private File path = null;
    private JTable table;
    private String[] text;
    private DefaultTableModel dtm;

    public Opener(File selectedFile, DefaultTableModel tableModel) {
        path=selectedFile;
        dtm=tableModel;
    }

    public JTable open(){
        table = new JTable(dtm);
        try {
            Scanner sc = new Scanner(path);
            while (sc.hasNext()){
                text = sc.nextLine().split(" ");

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return table;
    }



}
