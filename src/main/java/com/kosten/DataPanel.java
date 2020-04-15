package com.kosten;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentEvent;

public class DataPanel extends JPanel implements Runnable {
    double massCount = (double) 0;
    private double summMass(){
        double summ = 0;
        for (int i=0; i<tableModel.getRowCount(); i++){
            String st = (String) tableModel.getValueAt(i,1);
            summ += Double.parseDouble(st);
        }
        return summ;
    }
    double allMass;

    private Object[][]array=new String[][]{};
    private Object[] columnsHeader = new String[]{"Имя","Масса", "Х", "У"};

   private DefaultTableModel tableModel = new DefaultTableModel(array, columnsHeader);
   private JTable table= new JTable(tableModel);

   Box rezContent = new Box(BoxLayout.Y_AXIS);
   Label mass = new Label("Общая масса: "+massCount);
   Label x = new Label("x: ");
   Label y = new Label("y: ");


   private JButton addButton = new JButton("Добавить");
   private JButton deleteButton = new JButton("Удалить");

   public DataPanel() {

       setLayout(new GridBagLayout());

      // new Thread(this).start();
   }

   public void init(){
       new Thread(this).start();
       rezContent.add(mass);
       rezContent.add(x);
       rezContent.add(y);
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
       add(rezContent);



       addButton.addActionListener((e) -> {
           int rowCount = table.getRowCount();
           for (int i = 0; i < rowCount; i++) {
               for (int j = 0; j < 4; j++) {
                   //System.out.println(table.getValueAt(i,j));
                   tableModel.setValueAt(table.getValueAt(i, j), i, j);

               }
           }
           tableModel.addRow(new String[]{" ", "1.0", "0.0", "0.0"});

       });
   }

    @Override
    public void run() {
        while (true){
            try {
                allMass= summMass();
                mass.setText("Общая масса: "+allMass);
                System.out.println("Длинна массива: "+tableModel.getRowCount());
                this.repaint();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
