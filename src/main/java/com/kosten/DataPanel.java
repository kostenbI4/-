package com.kosten;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;

public class DataPanel extends JPanel implements Runnable {

    // Метод для подсчета общей массы
    double massCount = (double) 0;
    private double summMass(){
        double summ = 0;
        for (int i=0; i<tableModel.getRowCount(); i++){
            String st = (String) tableModel.getValueAt(i,1);
            summ += Double.parseDouble(st);
        }
        return summ;
    }
    double mxCount = (double) 0;
    double mxSumm(){
        double mxS =0;
        for (int i=0; i<tableModel.getRowCount(); i++){
            String st = (String) tableModel.getValueAt(i,1);
            String st1 = (String) tableModel.getValueAt(i,2);
            mxS+=Double.parseDouble(st)*Double.parseDouble(st1);
        }
        return mxS;
    }
    double myCount = (double) 0;
    double mySumm(){
        double myS =0;
        for (int i=0; i<tableModel.getRowCount(); i++){
            String st = (String) tableModel.getValueAt(i,1);
            String st1 = (String) tableModel.getValueAt(i,3);
            myS+=Double.parseDouble(st)*Double.parseDouble(st1);
        }
        return myS;
    }


    private Object[][]array=new String[][]{};
    private Object[] columnsHeader = new String[]{"Имя","Масса", "Х", "У"};

    private JMenuBar mb = new JMenuBar();
    private JMenu file = new JMenu("File");
    private JMenuItem open = new JMenuItem("Open");
    private JMenuItem save = new JMenuItem("Save");
    private  JFileChooser fileChooser = null;

   private DefaultTableModel tableModel = new DefaultTableModel(array, columnsHeader);
   private JTable table= new JTable(tableModel);

   Box rezContent = new Box(BoxLayout.Y_AXIS);
   Label mass = new Label("Общая масса: "+massCount);
   Label x = new Label("x: "+mxCount);
   Label y = new Label("y: "+myCount);



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
       // Запрет на перемещение колонок
       table.getTableHeader().setReorderingAllowed(false);

       file.add(open);
       file.add(save);
       mb.add(file);
       fileChooser = new JFileChooser();

       add(mb, new GridBagConstraints(0, 0, 1, 1, 1, 1,
               GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
               new Insets(1, 1, 1, 1), 0, 0));

       JScrollPane tableScrollPane = new JScrollPane(table);
       tableScrollPane.setPreferredSize(new Dimension(400, 400));

       add(tableScrollPane, new GridBagConstraints(0, 1, 2, 1, 1, 1,
               GridBagConstraints.NORTH, GridBagConstraints.BOTH,
               new Insets(1, 1, 1, 1), 0, 0));
       add(addButton, new GridBagConstraints(0, 2, 1, 1, 1, 1,
               GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
               new Insets(1, 1, 1, 1), 0, 0));
       add(deleteButton, new GridBagConstraints(1, 2, 1, 1, 1, 1,
               GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
               new Insets(1, 1, 1, 1), 0, 0));
       add(rezContent);



       addButton.addActionListener((e) -> {
           int rowCount = table.getRowCount();
           for (int i = 0; i < rowCount; i++) {
               for (int j = 0; j < 4; j++) {
                   tableModel.setValueAt(table.getValueAt(i, j), i, j);
               }
           }
           tableModel.addRow(new String[]{"-", "1.0", "0.0", "0.0"});

       });

       deleteButton.addActionListener((e) -> {
            int sr = table.getSelectedRow();
            if(sr>-1){
                tableModel.removeRow(sr);
            }

       });

       save.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               fileChooser.setDialogTitle("Сохранение файла");
               // Определение режима - только файл
               fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
               int result = fileChooser.showSaveDialog(DataPanel.this);
               // Если файл выбран, то представим его в сообщении
               if (result == JFileChooser.APPROVE_OPTION ) {
                   new Saver(fileChooser.getSelectedFile(), table);
                   System.out.println(fileChooser.getSelectedFile());
                   JOptionPane.showMessageDialog(DataPanel.this,
                           "Файл '" + fileChooser.getSelectedFile() +
                                   " ) сохранен");
               }
           }
       });

       open.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               fileChooser.setDialogTitle("Открытие файла");
               // Определение режима - только файл
               fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
               int result = fileChooser.showOpenDialog(DataPanel.this);
               // Если файл выбран, то представим его в сообщении
               if (result == JFileChooser.APPROVE_OPTION ) {
                   Opener op = new Opener(fileChooser.getSelectedFile(), tableModel);
                   tableModel.setRowCount(0);
                   table=op.open();
                   System.out.println(fileChooser.getSelectedFile());
                   JOptionPane.showMessageDialog(DataPanel.this,
                           "Файл '" + fileChooser.getSelectedFile() +
                                   " ) сохранен");
               }
           }
       });
   }

    @Override
    public void run() {
        while (true){
            try {

                massCount= summMass();
                mxCount=mxSumm();
                myCount=mySumm();
                mass.setText("Общая масса: "+massCount);
                x.setText("x: "+mxCount);
                y.setText("y: "+myCount);
               // System.out.println("Длинна массива: "+tableModel.getRowCount());
                this.repaint();
                Thread.sleep(500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
