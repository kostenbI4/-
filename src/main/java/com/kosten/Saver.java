package com.kosten;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Saver {
    File path = null;
    JTable table;
    String text="";

    public Saver(File selectedFile, JTable table) {
        this.path=selectedFile;
        this.table=table;
        save();
    }

    private void save() {
        try {
            String p = path+".txt";
            FileWriter fw = new FileWriter(p);
            for(int i =0; i<table.getRowCount(); i++){
                if(i>0) text+="\n";
                for (int j = 0; j < 4; j++) {
                    System.out.println(table.getValueAt(i,j));
                    text+= table.getValueAt(i, j)+"@#";
                }
            }

            fw.write(text);
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
