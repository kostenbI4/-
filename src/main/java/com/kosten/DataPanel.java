package com.kosten;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentEvent;

public class DataPanel extends JPanel implements Runnable {
   TableModel model;

   private TableModel tableModel = new TableModel();
   private JTable table= new JTable(tableModel);

   private JButton addButton = new JButton("Добавить");
   private JButton deleteButton = new JButton("Удалить");

   public DataPanel(TableModel model) {
       this.model=model;
       setLayout(new GridBagLayout());

       new Thread(this).start();
   }

   public void init(){
       JScrollPane tableScrollPane = new JScrollPane(table);
       tableScrollPane.setPreferredSize(new Dimension(400, 400));

       add(tableScrollPane, new GridBagConstraints(0, 0, 2, 1, 1, 1,
               GridBagConstraints.NORTH, GridBagConstraints.BOTH,
               new Insets(1, 1, 1, 1), 0, 0));
       add(addButton, new GridBagConstraints(0, 1, 1, 1, 1, 1,
               GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
               new Insets(1, 1, 1, 1), 0, 0));
       add(deleteButton, new GridBagConstraints(1, 1, 1, 1, 1, 1,
               GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
               new Insets(1, 1, 1, 1), 0, 0));



       addButton.addActionListener((e) -> {
           int rowCount = table.getRowCount();
           for (int i = 0; i < rowCount; i++) {
               for (int j = 0; j < 4; j++) {
                   //System.out.println(table.getValueAt(i,j));
                   tableModel.setValueAt(table.getValueAt(i, j), i, j);

               }
           }
           tableModel.addData(new String[]{" ", "0.0", "0.0", "0.0"});

       });
   }

    @Override
    public void run() {
        while (true){
            try {

                int rowCount = table.getRowCount();
                System.out.println(rowCount);
                for (int i = 0; i < rowCount; i++) {
                    for (int j = 0; j < 4; j++) {
                        tableModel.setValueAt(table.getValueAt(i, j), i, j);
                    }
                }
                this.repaint();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
